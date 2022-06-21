package com.sibs_test.sibs_test_felipe.database.mapper

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types


object RoomTypeConverters {
    private val moshi by lazy {
        MoshiFactory.build()
    }

    @TypeConverter
    @JvmStatic
    fun fromListOfStringsToJson(strings: List<String>?): String? {
        if (strings == null) return null
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(type)
        return adapter.toJson(strings)
    }

    @TypeConverter
    @JvmStatic
    fun toJsonToListOfStrings(json: String?): List<String>? {
        if (json == null) return null
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(type)
        return adapter.fromJson(json) ?: emptyList()
    }
}