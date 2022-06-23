package com.sibs_test.sibs_test_felipe.ui.book_store_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.data.repository.BookStoreRepository_Imp
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain
import com.sibs_test.sibs_test_felipe.extensions.Event
import com.sibs_test.sibs_test_felipe.utils.TinyDB
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookStoreDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val bookRepository: BookStoreRepository_Imp,
    private val tinyDB: TinyDB
) : ViewModel(), BookStoreDetailsContract.ViewModel {

    private val bookArgument = savedStateHandle.get<BookDomain>("book")!!

    private var cachedFavoriteList = tinyDB.getListString("cachedFavoriteList") ?: arrayListOf<String>()

    private val _error = MediatorLiveData<Resource.Error>()
    override val error: LiveData<Resource.Error> = _error

    private val _navigation = MediatorLiveData<Event<BookStoreDetailsContract.ViewInstructions>>()
    override val navigation: LiveData<Event<BookStoreDetailsContract.ViewInstructions>> = _navigation

    override val book: LiveData<BookDomain> = bookRepository.getBookById(bookArgument.id)

    override fun favoriteClicked(book: BookDomain, favoriteState: Boolean) {
        if (favoriteState) {
            cachedFavoriteList += book.id
            tinyDB.putListString("cachedFavoriteList", cachedFavoriteList)

            bookRepository.favoriteBookSync(book.id, favoriteState)
        } else {
            cachedFavoriteList.remove(book.id)
            tinyDB.putListString("cachedFavoriteList", cachedFavoriteList)

            bookRepository.favoriteBookSync(book.id, favoriteState)
        }
    }

    override fun onBackClicked() {
        _navigation.postValue(Event(BookStoreDetailsContract.ViewInstructions.NavigateBack))
    }
}