package com.sibs_test.sibs_test_felipe.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.sibs_test.sibs_test_felipe.core.*
import com.sibs_test.sibs_test_felipe.data.mapper.MapperBookNetworkToBookEntity
import com.sibs_test.sibs_test_felipe.data.paginated.BookPagedBoundaryCallback
import com.sibs_test.sibs_test_felipe.database.dao.BooksDao
import com.sibs_test.sibs_test_felipe.domain.mapper.MapperBookEntityToBookDomain
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain
import com.sibs_test.sibs_test_felipe.extensions.observeOnce
import com.sibs_test.sibs_test_felipe.extensions.toExecutor
import com.sibs_test.sibs_test_felipe.network.BookStoreService
import com.sibs_test.sibs_test_felipe.network.model_result.BookResult
import com.sibs_test.sibs_test_felipe.utils.LivePagedListBuilderUtils
import javax.inject.Inject

class BookStoreRepository_Imp @Inject constructor(
    private val bookStoreService: BookStoreService,
    private val booksDao: BooksDao,
    private val appExecutors: AppExecutors
) : BookStoreRepository {

    override fun getBookById(id: String): LiveData<BookDomain> {
        return booksDao.getBookById(id).map {
            MapperBookEntityToBookDomain.mapFromEntity(it)
        }
    }

    override fun favoriteBookSync(bookId: String, favoriteState: Boolean) {
        appExecutors.diskIo().execute {
            booksDao.bookFavoriteState(bookId, favoriteState)
        }
    }

    override fun getBookStoreListPaged(): PagedListing<BookDomain> {

        appExecutors.diskIo().execute {
            booksDao.deleteAll()
        }

        val boundaryCallback = BookPagedBoundaryCallback(
            bookStoreService = bookStoreService,
            handleResponse = this::handlePagedBookResponse,
            retryExecutor = appExecutors.networkIo(),
            networkPageSize = 20
        )

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        val dataSourceFactory = booksDao.getAllPagedBooks().map {
            MapperBookEntityToBookDomain.mapFromEntity(it)
        }


        val pagedList = LivePagedListBuilderUtils.build(
            null,
            pagedListConfig,
            boundaryCallback,
            dataSourceFactory,
            EpoxyAsyncUtil.getAsyncBackgroundHandler().toExecutor(),
            appExecutors.networkIo()
        )

        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState: LiveData<Resource<Unit>> = refreshTrigger.switchMap {
            refresh()
        }.map {
            if (it is Resource.Success) {
                boundaryCallback.nextPageToLoad = 21
            }
            it
        }

        return PagedListing(
            pagedList = pagedList,
            networkState = boundaryCallback.networkState,
            retry = {
                boundaryCallback.helper.retryAllFailed()
            },
            refresh = {
                refreshTrigger.value = Unit
            },
            refreshState = refreshState
        )
    }

    sealed class OverrideInstruction {
        object NotOverride : OverrideInstruction()
        data class OverrideAllById(val id: String) : OverrideInstruction()
    }

    @WorkerThread
    private fun handlePagedBookResponse(
        users: List<BookResult>,
        overrideExist: Boolean,
        onComplete: () -> Unit
    ) {
        val overrideInstruction = OverrideInstruction.NotOverride

        insertUsersOnDB(users, overrideInstruction)
        onComplete()
    }

    @WorkerThread
    private fun insertUsersOnDB(
        users: List<BookResult>,
        overrideInstruction: OverrideInstruction
    ) {
        appExecutors.diskIo().execute {
            users.forEach {
                when (overrideInstruction) {
                    OverrideInstruction.NotOverride -> {
                    }
                    is OverrideInstruction.OverrideAllById -> {
                        booksDao.deleteById(it.id)
                    }
                }
                booksDao.upsert(MapperBookNetworkToBookEntity.mapFromNetwork(it))
            }

        }
    }

    @MainThread
    private fun refresh(): LiveData<Resource<Unit>> {
        val networkState = MutableLiveData<Resource<Unit>>()
        networkState.value = Resource.Loading(Unit)

        val request = bookStoreService.getBookStoreListPaged()
        request.observeOnce(Observer { response ->
            when (response) {
                is ApiErrorResponse -> {
                    networkState.value = Resource.Failure(Unit, Resource.Error(response.message))
                }
                is ApiSuccessResponse -> {
                    appExecutors.diskIo().execute {
                        booksDao.deleteAll()
                        response.data.forEach {
                            booksDao.insertOrIgnore(MapperBookNetworkToBookEntity.mapFromNetwork(it))
                        }

                        networkState.postValue(Resource.Success(Unit))
                    }
                }
                is ErrorResponse -> networkState.value = Resource.Failure(Unit, Resource.Error(response.message))
                null -> throw IllegalStateException()
            }
        })

        return networkState
    }
}