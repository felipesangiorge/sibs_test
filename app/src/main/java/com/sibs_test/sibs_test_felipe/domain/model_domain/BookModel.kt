package com.sibs_test.sibs_test_felipe.domain.model_domain


data class BookDomain(
    val id: String,
    val kind: String,
    val selflink: String,
    val volumeInfo: VolumeInfoDomain,
    val publishedDate: String,
    val description: String,
    val saleInfo: SalesInfoDomain,
    val industryIdentifiers: IndustryIdentifiersDomain
) : DomainModel

data class VolumeInfoDomain(
    val title: String,
    val subtitle: String,
    val authors: List<String>
) : DomainModel

data class SalesInfoDomain(
    val buyLink: String?
) : DomainModel

data class IndustryIdentifiersDomain(
    val imageLink: ThumbnailsInfoDomain
) : DomainModel

data class ThumbnailsInfoDomain(
    val smallThumbnail: String,
    val thumbnail: String
) : DomainModel
