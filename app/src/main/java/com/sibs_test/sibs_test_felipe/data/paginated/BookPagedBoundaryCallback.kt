package com.sibs_test.sibs_test_felipe.data.paginated

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.sibs_test.sibs_test_felipe.core.*
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain
import com.sibs_test.sibs_test_felipe.network.BookStoreService
import com.sibs_test.sibs_test_felipe.network.model_result.BookResult
import java.util.concurrent.Executor
import javax.inject.Inject

class BookPagedBoundaryCallback @Inject constructor(
    private val bookStoreService: BookStoreService,
    private val handleResponse: (events: List<BookResult>, overrideExisting: Boolean, onComplete: () -> Unit) -> Unit,
    private val retryExecutor: Executor,
    private val networkPageSize: Int
) : PagedList.BoundaryCallback<BookDomain>() {


    var nextPageToLoad = 1
    val helper = PagingRequestHelper(retryExecutor)

    //    val networkState = helper.createStatusLiveData()
    val networkState = MutableLiveData<Resource<Unit>>()


    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    @MainThread
    override fun onZeroItemsLoaded() {
        retryExecutor.execute {
            helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
                val loadedPage = nextPageToLoad
                networkState.postValue(Resource.Loading(Unit))
                val response = bookStoreService.getBookStoreListSync()
                when (response) {
                    is ApiSuccessResponse -> {
                        nextPageToLoad++
                        insertItemsIntoDb(response.data, it, loadedPage == 1)
                    }
                    is ApiErrorResponse -> {
                        networkState.postValue(Resource.Failure(Unit, Resource.Error(response.message)))
                        it.recordFailure(Resource.Error(response.message))
                    }
                    is ErrorResponse -> {
                        networkState.postValue(Resource.Failure(Unit, Resource.Error(response.message)))
                        it.recordFailure(Resource.Error(response.message))
                    }
                }
            }
        }
    }

    /**
     * User reached to the end of the list.
     */
    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: BookDomain) {
        retryExecutor.execute {
            helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
                val loadedPage = nextPageToLoad
                networkState.postValue(Resource.Loading(Unit))
                val response = bookStoreService.getBookStoreListSync()
                when (response) {
                    is ApiSuccessResponse -> {
                        nextPageToLoad++
                        insertItemsIntoDb(response.data, it, loadedPage == 1)
                    }
                    is ApiErrorResponse -> {
                        networkState.postValue(Resource.Failure(Unit, Resource.Error(response.message)))
                        it.recordFailure(Resource.Error(response.message))
                    }
                    is ErrorResponse -> {
                        networkState.postValue(Resource.Failure(Unit, Resource.Error(response.message)))
                        it.recordFailure(Resource.Error(response.message))
                    }
                }
            }
        }
    }


    /**
     * every time it gets new items, boundary callback simply inserts them into the database and
     * paging library takes care of refreshing the list if necessary.
     */
    private fun insertItemsIntoDb(
        response: List<BookResult>,
        requestCallback: PagingRequestHelper.Request.Callback,
        overrideExisting: Boolean
    ) {
        handleResponse(response, overrideExisting) {
            networkState.postValue(Resource.Success(Unit))
            requestCallback.recordSuccess()
        }
    }

    override fun onItemAtFrontLoaded(itemAtFront: BookDomain) {}
}