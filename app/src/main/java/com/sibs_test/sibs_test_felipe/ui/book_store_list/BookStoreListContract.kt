package com.sibs_test.sibs_test_felipe.ui.book_store_list

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain
import com.sibs_test.sibs_test_felipe.extensions.Event

interface BookStoreListContract {

    interface ViewModel : ViewState, ViewActions

    interface ViewState {
        val error: LiveData<Resource.Error>
        val navigation: LiveData<Event<ViewInstructions>>
        val pagedBooks: LiveData<PagedList<BookDomain>>
        val favoriteList: LiveData<ArrayList<String>>
        val favoriteState: LiveData<Boolean>
    }

    interface ViewActions {
        fun bookItemClicked(book: BookDomain)

        fun refreshFavoriteList()

        fun favoriteFilterClicked()
    }

    sealed class ViewInstructions {
        data class NavigateToBookDetails(val book: BookDomain) : ViewInstructions()
    }
}