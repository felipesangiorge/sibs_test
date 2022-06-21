package com.sibs_test.sibs_test_felipe.ui.book_store_list

import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.sibs_test.sibs_test_felipe.R
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain
import com.sibs_test.sibs_test_felipe.extensions.KotlinEpoxyHolder


@EpoxyModelClass(layout = R.layout.item_book_list)
abstract class BookStoreItemModel : EpoxyModelWithHolder<BookStoreItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var book: BookDomain

    override fun bind(holder: Holder) = with(holder) {
        super.bind(holder)

    }

    class Holder : KotlinEpoxyHolder() {
        val iv_thumbnail by bind<ImageView>(R.id.iv_thumbnail)
        val tv_title by bind<TextView>(R.id.tv_title)
        val tv_publication by bind<TextView>(R.id.tv_publication)
        val tv_description by bind<TextView>(R.id.iv_thumbnail)
        val iv_buy_availability by bind<ImageView>(R.id.tv_publication)
    }
}