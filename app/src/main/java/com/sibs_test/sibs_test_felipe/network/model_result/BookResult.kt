package com.sibs_test.sibs_test_felipe.network.model_result

data class BookResult(
    val id: String,
    val kind: String,
    val selflink: String,
    val volumeInfo: VolumeInfoResult,
    val publishedDate: String,
    val description: String,
    val saleInfo: SalesInfoResult,
    val industryIdentifiers: IndustryIdentifiersResult
) : NetworkModel


data class VolumeInfoResult(
    val title: String,
    val subtitle: String,
    val authors: List<String>
) : NetworkModel

data class SalesInfoResult(
    val buyLink: String?
) : NetworkModel

data class IndustryIdentifiersResult(
    val imageLink: ThumbnailsInfoResult
) : NetworkModel

data class ThumbnailsInfoResult(
    val smallThumbnail: String,
    val thumbnail: String
) : NetworkModel
