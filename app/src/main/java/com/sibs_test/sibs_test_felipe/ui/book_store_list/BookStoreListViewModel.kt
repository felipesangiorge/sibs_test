package com.sibs_test.sibs_test_felipe.ui.book_store_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.data.repository.BookStoreRepository_Imp
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

    init {

    }

}