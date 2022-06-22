package com.sibs_test.sibs_test_felipe.ui.book_store_list

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain

class BookStoreListPagedController(
    private val bookClicked: (book: BookDomain) -> Unit
) :
    PagedListEpoxyController<BookDomain>(
        EpoxyAsyncUtil.getAsyncBackgroundHandler(),
        EpoxyAsyncUtil.getAsyncBackgroundHandler()
    ) {

    override fun buildItemModel(currentPosition: Int, item: BookDomain?): EpoxyModel<*> {
        if (item == null) throw UnsupportedOperationException()
        return BookStoreItemModel_()
            .id(item.id)
            .book(item)
            .bookClick(bookClicked)
    }
}