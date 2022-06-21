package com.sibs_test.sibs_test_felipe.ui.book_store_list

import androidx.lifecycle.LiveData
import com.sibs_test.sibs_test_felipe.core.Resource

interface BookStoreListContract {

    interface ViewModel : ViewState, ViewActions

    interface ViewState {
        val error: LiveData<Resource.Error>
        val navigation: LiveData<ViewInstructions>
    }

    interface ViewActions {

    }

    sealed class ViewInstructions {

    }
}