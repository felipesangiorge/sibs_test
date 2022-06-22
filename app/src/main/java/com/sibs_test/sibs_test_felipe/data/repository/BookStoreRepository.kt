package com.sibs_test.sibs_test_felipe.data.repository

import com.sibs_test.sibs_test_felipe.core.PagedListing
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain

interface BookStoreRepository {

    fun getBookStoreListPaged(): PagedListing<BookDomain>
}