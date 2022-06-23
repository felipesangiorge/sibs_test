package com.sibs_test.sibs_test_felipe.data.repository

import androidx.lifecycle.LiveData
import com.sibs_test.sibs_test_felipe.core.PagedListing
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain

interface BookStoreRepository {

    fun getBookStoreListPaged(): PagedListing<BookDomain>

    fun favoriteBookSync(bookId: String, favoriteState: Boolean)

    fun getBookById(id: String): LiveData<BookDomain>
}