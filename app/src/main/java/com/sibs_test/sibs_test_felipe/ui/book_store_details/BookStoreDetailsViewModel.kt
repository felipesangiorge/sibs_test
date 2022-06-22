package com.sibs_test.sibs_test_felipe.ui.book_store_details

import androidx.lifecycle.*
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain
import com.sibs_test.sibs_test_felipe.utils.TinyDB
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookStoreDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val tinyDB: TinyDB
) : ViewModel(), BookStoreDetailsContract.ViewModel {

    private val bookArgument = savedStateHandle.get<BookDomain>("book")!!

    private var cachedFavoriteList = tinyDB.getListString("cachedFavoriteList") ?: arrayListOf<String>()

    private val _error = MediatorLiveData<Resource.Error>()
    override val error: LiveData<Resource.Error> = _error

    private val _navigation = MediatorLiveData<BookStoreDetailsContract.ViewInstructions>()
    override val navigation: LiveData<BookStoreDetailsContract.ViewInstructions> = _navigation

    private val _book = MutableLiveData<BookDomain>(bookArgument)
    override val book: LiveData<BookDomain> = _book

    private val _isFavoriteBook = MutableLiveData(Pair(cachedFavoriteList.contains(bookArgument.id), bookArgument))
    override val isFavoriteBook: LiveData<Pair<Boolean, BookDomain>> = _isFavoriteBook

    override fun favoriteClicked(book: BookDomain, favoriteState: Boolean) {
        if (favoriteState) {
            cachedFavoriteList += book.id
            tinyDB.putListString("cachedFavoriteList", cachedFavoriteList)

            _isFavoriteBook.value = Pair(favoriteState, book)
        } else {
            cachedFavoriteList.remove(book.id)
            tinyDB.putListString("cachedFavoriteList", cachedFavoriteList)

            _isFavoriteBook.value = Pair(favoriteState, book)
        }
    }

    override fun onBackClicked() {
        _navigation.value = BookStoreDetailsContract.ViewInstructions.NavigateBack
    }
}