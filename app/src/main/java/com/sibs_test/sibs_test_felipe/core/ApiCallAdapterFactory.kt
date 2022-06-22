package com.sibs_test.sibs_test_felipe.core

import com.sibs_test.sibs_test_felipe.network.BaseResponse
import com.squareup.moshi.Types
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiCallAdapterFactory() : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != ApiResponse::class.java) return null

        if (returnType !is ParameterizedType) throw IllegalArgumentException("Resource have to be parameterized")

        val bodyType = getParameterUpperBound(0, returnType)
        val type = Types.newParameterizedType(BaseResponse::class.java, bodyType)

        return ApiCallAdapter<Any>(type, retrofit)
    }
}