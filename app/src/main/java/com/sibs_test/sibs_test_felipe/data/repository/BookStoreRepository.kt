package com.sibs_test.sibs_test_felipe.data.repository

import androidx.lifecycle.LiveData
import com.sibs_test.sibs_test_felipe.core.Resource
import com.sibs_test.sibs_test_felipe.network.model_result.BookResult

interface BookStoreRepository {

    fun getBookStoreList(): LiveData<Resource<List<BookResult>>>

}