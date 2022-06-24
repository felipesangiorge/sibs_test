package com.sibs_test.sibs_test_felipe.ui.book_store_list

import androidx.lifecycle.*
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.data.repository.BookStoreRepository_Imp
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain
import com.sibs_test.sibs_test_felipe.extensions.Event
import com.sibs_test.sibs_test_felipe.extensions.NonnullMediatorLiveData
import com.sibs_test.sibs_test_felipe.utils.TinyDB
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookStoreListViewModel @Inject constructor(
    private val bookStoreRepository: BookStoreRepository_Imp,
    private val tinyDB: TinyDB
) : ViewModel(), BookStoreListContract.ViewModel {

    private val _error = MediatorLiveData<Resource.Error>()
    override val error: LiveData<Resource.Error> = _error

    private val _navigation = MediatorLiveData<Event<BookStoreListContract.ViewInstructions>>()
    override val navigation: LiveData<Event<BookStoreListContract.ViewInstructions>> = _navigation

    private val pagedBook = NonnullMediatorLiveData(bookStoreRepository.getBookStoreListPaged())

    override val pagedBooks = pagedBook.switchMap {
        it.pagedList
    }

    val networkStage = pagedBook.switchMap {
        it.networkState
    }

    val refreshState = pagedBook.switchMap {
        it.refreshState
    }

    private val _favoriteList = MutableLiveData(Unit)
    override val favoriteList: LiveData<ArrayList<String>> = _favoriteList.map {
        tinyDB.getListString("cachedFavoriteList")
    }

    private val _favoriteState = MutableLiveData(false)
    override val favoriteState: LiveData<Boolean> = _favoriteState

    init {
    }

    override fun refreshFavoriteList() {
        _favoriteList.value = Unit
    }

    override fun favoriteFilterClicked() {
        _favoriteState.value = _favoriteState.value?.not()
    }

    override fun bookItemClicked(book: BookDomain) {
        _navigation.postValue(Event(BookStoreListContract.ViewInstructions.NavigateToBookDetails(book)))
    }
}