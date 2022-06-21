package com.sibs_test.sibs_test_felipe.data.repository

import androidx.lifecycle.LiveData
import com.sibs_test.sibs_test_felipe.core.ApiErrorResponse
import com.sibs_test.sibs_test_felipe.core.ApiSuccessResponse
import com.sibs_test.sibs_test_felipe.core.ErrorResponse
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.network.BookStoreService
import com.sibs_test.sibs_test_felipe.network.model_result.BookResult
import com.sibs_test.sibs_test_felipe.database.dao.BooksDao
import com.sibs_test.sibs_test_felipe.extensions.NonnullMediatorLiveData
import javax.inject.Inject

class BookStoreRepository_Imp @Inject constructor(
    private val bookStoreService: BookStoreService,
    private val booksDao: BooksDao
) : BookStoreRepository {

    override fun getBookStoreList(): LiveData<Resource<List<BookResult>>> {
        val response = bookStoreService.getBookStoreList()

        val result = NonnullMediatorLiveData<Resource<List<BookResult>>>(Resource.Loading(null))

        result.addSource(response) {
            when (it) {
                is ApiErrorResponse -> result.value = Resource.Failure(null, Resource.Error(it.message))
                is ApiSuccessResponse -> result.value = Resource.Success(it.data)
                is ErrorResponse -> result.value = Resource.Failure(null, Resource.Error(it.message))
            }
        }
        return result
    }
}