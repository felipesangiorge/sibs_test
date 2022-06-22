package com.sibs_test.sibs_test_felipe.ui.book_store_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.data.repository.BookStoreRepository_Imp
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain
import com.sibs_test.sibs_test_felipe.extensions.NonnullMediatorLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookStoreListViewModel @Inject constructor(
    private val bookStoreRepository: BookStoreRepository_Imp
) : ViewModel(), BookStoreListContract.ViewModel {

    private val _error = MediatorLiveData<Resource.Error>()
    override val error: LiveData<Resource.Error> = _error

    private val _navigation = MediatorLiveData<BookStoreListContract.ViewInstructions>()
    override val navigation: LiveData<BookStoreListContract.ViewInstructions> = _navigation

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

    init {
    }

    override fun bookItemClicked(book: BookDomain) {
        _navigation.value = BookStoreListContract.ViewInstructions.NavigateToBookDetails(book)
    }
}