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

    var favoriteList: ArrayList<String>? = null
        set(value) {
            field = value
            requestForcedModelBuild()
        }

    var favorite: Boolean = false
        set(value) {
            field = value
            requestForcedModelBuild()
        }

    override fun buildItemModel(currentPosition: Int, item: BookDomain?): EpoxyModel<*> {
        setFilterDuplicates(true)
        if (item == null) throw UnsupportedOperationException()
        return BookStoreItemModel_()
            .id(
                if (favorite) {
                    if (favoriteList?.contains(item.id) == true) item.id else "favorite"
                } else {
                    item.id
                }
            )
            .book(item)
            .bookClick(bookClicked)
            .favorite(favoriteList?.contains(item.id) ?: false)
    }
}