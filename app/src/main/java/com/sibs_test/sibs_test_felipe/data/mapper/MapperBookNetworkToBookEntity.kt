package com.sibs_test.sibs_test_felipe.data.mapper

import com.sibs_test.sibs_test_felipe.database.model_entity.*
import com.sibs_test.sibs_test_felipe.network.model_result.*

object MapperBookNetworkToBookEntity : MapperNetworkModelToEntityModel<BookResult, BookEntity> {

    override fun mapFromNetwork(type: BookResult): BookEntity = BookEntity(
        type.id,
        type.kind,
        type.selflink,
        MapperVolumeInfoNetworkToVolumeInfoEntity.mapFromNetwork(type.volumeInfo),
        type.saleInfo?.let {
            MapperSalesInfoNetworkToSalesInfoEntity.mapFromNetwork(type.saleInfo)
        }
    )
}

object MapperVolumeInfoNetworkToVolumeInfoEntity : MapperNetworkModelToEntityModel<VolumeInfoResult, VolumeInfoEntity> {
    override fun mapFromNetwork(type: VolumeInfoResult): VolumeInfoEntity = VolumeInfoEntity(
        type.title,
        type.subtitle,
        type.authors,
        type.publishedDate,
        type.description,
        MapperThumbnailsInfoNetworkToThumbnailsInfoEntity.mapFromNetwork(type.imageLinks)

    )
}

object MapperSalesInfoNetworkToSalesInfoEntity : MapperNetworkModelToEntityModel<SalesInfoResult, SalesInfoEntity> {
    override fun mapFromNetwork(type: SalesInfoResult): SalesInfoEntity = SalesInfoEntity(
        type.buyLink,
    )
}

object MapperIndustryIdentifiersNetworkToIndustryIdentifiersEntity : MapperNetworkModelToEntityModel<IndustryIdentifiersResult, IndustryIdentifiersEntity> {
    override fun mapFromNetwork(type: IndustryIdentifiersResult): IndustryIdentifiersEntity = IndustryIdentifiersEntity(
        type.imageLinks?.let {
            MapperThumbnailsInfoNetworkToThumbnailsInfoEntity.mapFromNetwork(type.imageLinks)
        }
    )
}

object MapperThumbnailsInfoNetworkToThumbnailsInfoEntity : MapperNetworkModelToEntityModel<ThumbnailsInfoResult, ThumbnailsInfoEntity> {
    override fun mapFromNetwork(type: ThumbnailsInfoResult): ThumbnailsInfoEntity = ThumbnailsInfoEntity(
        type.thumbnail,
        type.smallThumbnail
    )
}