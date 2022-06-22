package com.sibs_test.sibs_test_felipe.domain.model_domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookDomain(
    val id: String,
    val kind: String,
    val selflink: String?,
    val volumeInfo: VolumeInfoDomain,
    val saleInfo: SalesInfoDomain?
) : DomainModel, Parcelable

@Parcelize
data class VolumeInfoDomain(
    val title: String,
    val subtitle: String?,
    val authors: List<String>,
    val publishedDate: String,
    val description: String?,
    val imageLinks: ThumbnailsInfoDomain
) : DomainModel, Parcelable

@Parcelize
data class SalesInfoDomain(
    val buyLink: String?
) : DomainModel, Parcelable

@Parcelize
data class IndustryIdentifiersDomain(
    val imageLinks: ThumbnailsInfoDomain
) : DomainModel, Parcelable

@Parcelize
data class ThumbnailsInfoDomain(
    val smallThumbnail: String?,
    val thumbnail: String?
) : DomainModel, Parcelable
