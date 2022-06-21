package com.sibs_test.sibs_test_felipe.ui.book_store_list

import android.util.Log
import androidx.lifecycle.*
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

    private val getBookStoreListAction = MutableLiveData(Unit)
    private val getBookStoreListResult = getBookStoreListAction.switchMap {
        bookStoreRepository.getBookStoreList()
    }

    init {
        _error.addSource(getBookStoreListResult){
            when(it){
                is Resource.Failure -> Log.e("Fail","${it.error.message}")
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Log.e("Success","${it.data}")
                }
            }
        }
    }

    override fun bookItemClicked() {

    }
}