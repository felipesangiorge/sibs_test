package com.sibs_test.sibs_test_felipe.ui.book_store_details

import androidx.lifecycle.*
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookStoreDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), BookStoreDetailsContract.ViewModel {

    private val bookArgument = savedStateHandle.get<BookDomain>("book")

    private val _error = MediatorLiveData<Resource.Error>()
    override val error: LiveData<Resource.Error> = _error

    private val _navigation = MediatorLiveData<BookStoreDetailsContract.ViewInstructions>()
    override val navigation: LiveData<BookStoreDetailsContract.ViewInstructions> = _navigation

    private val _book = MutableLiveData<BookDomain>(bookArgument)
    override val book: LiveData<BookDomain> = _book

    override fun onBackClicked() {
        _navigation.value = BookStoreDetailsContract.ViewInstructions.NavigateBack
    }
}