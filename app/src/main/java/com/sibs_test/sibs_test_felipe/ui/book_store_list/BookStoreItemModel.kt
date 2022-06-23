package com.sibs_test.sibs_test_felipe.ui.book_store_list

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.sibs_test.sibs_test_felipe.R
import com.sibs_test.sibs_test_felipe.domain.model_domain.BookDomain
import com.sibs_test.sibs_test_felipe.extensions.KotlinEpoxyHolder
import com.sibs_test.sibs_test_felipe.extensions.toVisibility


@EpoxyModelClass(layout = R.layout.item_book_list)
abstract class BookStoreItemModel : EpoxyModelWithHolder<BookStoreItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var book: BookDomain

    @EpoxyAttribute
    var favorite: Boolean = false

    @EpoxyAttribute
    lateinit var bookClick: (book: BookDomain) -> Unit

    override fun bind(holder: Holder): Unit = with(holder) {

        book.volumeInfo.imageLinks?.thumbnail?.let {
            Glide.with(iv_thumbnail)
                .load(it.replace("http", "https"))
                .error(R.drawable.ic_image_placeholder)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(iv_thumbnail)
        }

        tv_title.text = book.volumeInfo.title
        tv_description.text = book.volumeInfo.description
        iv_buy_availability.toVisibility = book.saleInfo?.buyLink != null
        iv_favorite.toVisibility = favorite

        top_parent.setOnClickListener {
            bookClick.invoke(book)
        }

    }

    class Holder : KotlinEpoxyHolder() {
        val top_parent by bind<CardView>(R.id.top_parent)
        val iv_thumbnail by bind<ImageView>(R.id.iv_thumbnail)
        val tv_title by bind<TextView>(R.id.tv_title)
        val tv_description by bind<TextView>(R.id.tv_description)
        val iv_buy_availability by bind<ImageView>(R.id.iv_buy_availability)
        val iv_favorite by bind<ImageView>(R.id.iv_favorite)
    }
}