package com.sibs_test.sibs_test_felipe.network

import javax.inject.Inject

class BookStoreService @Inject constructor(private val bookStoreApi: ApiRequest.BookStoreApi) {

    fun getBookStoreListSync(startIndex: Int) = bookStoreApi.getBookListSync(startIndex = startIndex)

    fun getBookStoreListPaged() = bookStoreApi.getBookListPaged()
}