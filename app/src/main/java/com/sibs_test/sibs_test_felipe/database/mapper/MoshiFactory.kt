package com.sibs_test.sibs_test_felipe.database.mapper

import com.squareup.moshi.Moshi

object MoshiFactory {
    private val instance by lazy {
        val moshi = Moshi.Builder()
            .build()
        moshi
    }

    fun build(): Moshi = instance
}