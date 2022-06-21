package com.sibs_test.sibs_test_felipe.data.network.model_result

data class BookResult(
    val kind: String,
    val id: String,
    val selflink: String,
    val volumeInfo: VolumeInfoResult,
    val publishedDate: String,
    val description: String,
    val saleInfo: SalesInfoResult
): NetworkModule


data class VolumeInfoResult(
    val title: String,
    val subtitle: String,
    val authors: List<String>
): NetworkModule

data class SalesInfoResult(
    val country: String,
    val buyLink: String?
): NetworkModule
