package com.sibs_test.sibs_test_felipe.ui.book_store_list

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain

interface BookStoreListContract {

    interface ViewModel : ViewState, ViewActions

    interface ViewState {
        val error: LiveData<Resource.Error>
        val navigation: LiveData<ViewInstructions>
        val pagedBooks: LiveData<PagedList<BookDomain>>
    }

    interface ViewActions {
        fun bookItemClicked(book: BookDomain)
    }

    sealed class ViewInstructions {
        data class NavigateToBookDetails(val book: BookDomain): ViewInstructions()
    }
}