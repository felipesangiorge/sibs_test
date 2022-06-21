package com.sibs_test.sibs_test_felipe.data.repository

import com.sibs_test.sibs_test_felipe.data.network.BookStoreService
import javax.inject.Inject

class BookStoreRepository_Imp @Inject constructor(
    private val bookStoreService: BookStoreService
) : BookStoreRepository {
}