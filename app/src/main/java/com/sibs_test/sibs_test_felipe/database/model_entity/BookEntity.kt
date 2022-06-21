package com.sibs_test.sibs_test_felipe.database.model_entity

import androidx.room.*
import com.sibs_test.sibs_test_felipe.database.mapper.RoomTypeConverters

@Entity
data class BookEntity(
    @PrimaryKey
    @ColumnInfo(name = "book_id") val id: String,
    @ColumnInfo(name = "book_kind") val kind: String,
    @ColumnInfo(name = "book_selflink") val selflink: String,
    @Embedded(prefix = "book_")
    val volumeInfo: VolumeInfoEntity,
    @ColumnInfo(name = "book_publishedDate") val publishedDate: String,
    @ColumnInfo(name = "book_description") val description: String,
    @Embedded(prefix = "book_")
    val saleInfo: SalesInfoEntity,
    @Embedded(prefix = "book_")
    val industryIdentifiersEntity: IndustryIdentifiersEntity
) : EntityModel

@Entity
data class VolumeInfoEntity(
    @ColumnInfo(name = "volume_info_title") val title: String,
    @ColumnInfo(name = "volume_info_subtitle") val subtitle: String,
    @field:TypeConverters(RoomTypeConverters::class)
    @ColumnInfo(name = "volume_info_authors") val authors: List<String>
) : EntityModel

@Entity
data class SalesInfoEntity(
    @ColumnInfo(name = "sales_info_buyLink") val buyLink: String?
) : EntityModel

@Entity
data class IndustryIdentifiersEntity(
    @Embedded
    val imageLink: ThumbnailsInfoEntity
) : EntityModel

@Entity
data class ThumbnailsInfoEntity(
    @ColumnInfo(name = "smallThumbnail") val smallThumbnail: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String
) : EntityModel