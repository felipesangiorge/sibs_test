package com.sibs_test.sibs_test_felipe.network

import javax.inject.Inject

class BookStoreService @Inject constructor(private val bookStoreApi: ApiRequest.BookStoreApi) {
    fun getBookStoreList() = bookStoreApi.getBookList()

    fun getBookStoreListSync() = bookStoreApi.getBookListSync()

}