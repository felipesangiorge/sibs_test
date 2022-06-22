package com.sibs_test.sibs_test_felipe.ui.book_store_details

import androidx.lifecycle.LiveData
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain

interface BookStoreDetailsContract {

    interface ViewModel: ViewState, ViewActions

    interface ViewState{
        val error: LiveData<Resource.Error>
        val navigation: LiveData<ViewInstructions>
        val book: LiveData<BookDomain>
    }

    interface ViewActions{
        fun onBackClicked()
    }

    sealed class ViewInstructions{
        object NavigateBack: ViewInstructions()
    }
}