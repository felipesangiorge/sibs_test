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
): NetworkModule


data class VolumeInfoResult(
    val title: String,
    val subtitle: String,
    val authors: List<String>
): NetworkModule

data class SalesInfoResult(
    val buyLink: String?
): NetworkModule

data class IndustryIdentifiersResult(
    val imageLink: ThumbnailsInfoResult
): NetworkModule

data class ThumbnailsInfoResult(
    val smallThumbnail: String,
    val thumbnail: String
): NetworkModule
