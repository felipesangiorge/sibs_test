package com.sibs_test.sibs_test_felipe.domain.mapper

import com.sibs_test.sibs_test_felipe.database.model_entity.*
import com.sibs_test.sibs_test_felipe.domain.model_domain.*

object MapperBookEntityToBookDomain : MapperEntityModelToDomainModel<BookEntity, BookDomain> {

    override fun mapFromEntity(type: BookEntity): BookDomain = BookDomain(
        type.id,
        type.kind,
        type.selflink,
        MapperVolumeInfoEntityToVolumeInfoDomain.mapFromEntity(type.volumeInfo),
        type.saleInfo?.let {
            MapperSalesInfoEntityToSalesInfoDomain.mapFromEntity(type.saleInfo)
        }
    )
}

object MapperVolumeInfoEntityToVolumeInfoDomain : MapperEntityModelToDomainModel<VolumeInfoEntity, VolumeInfoDomain> {
    override fun mapFromEntity(type: VolumeInfoEntity): VolumeInfoDomain = VolumeInfoDomain(
        type.title,
        type.subtitle,
        type.authors,
        type.publishedDate,
        type.description,
        MapperThumbnailsInfoEntityToThumbnailsInfoDomain.mapFromEntity(type.imageLink)
    )
}

object MapperSalesInfoEntityToSalesInfoDomain : MapperEntityModelToDomainModel<SalesInfoEntity, SalesInfoDomain> {
    override fun mapFromEntity(type: SalesInfoEntity): SalesInfoDomain = SalesInfoDomain(
        type.buyLink,
    )
}

object MapperIndustryIdentifiersEntityToIndustryIdentifiersDomain : MapperEntityModelToDomainModel<IndustryIdentifiersEntity, IndustryIdentifiersDomain> {
    override fun mapFromEntity(type: IndustryIdentifiersEntity): IndustryIdentifiersDomain = IndustryIdentifiersDomain(
        MapperThumbnailsInfoEntityToThumbnailsInfoDomain.mapFromEntity(type.imageLink)
    )
}

object MapperThumbnailsInfoEntityToThumbnailsInfoDomain : MapperEntityModelToDomainModel<ThumbnailsInfoEntity, ThumbnailsInfoDomain> {
    override fun mapFromEntity(type: ThumbnailsInfoEntity): ThumbnailsInfoDomain = ThumbnailsInfoDomain(
        type.thumbnail,
        type.smallThumbnail
    )
}