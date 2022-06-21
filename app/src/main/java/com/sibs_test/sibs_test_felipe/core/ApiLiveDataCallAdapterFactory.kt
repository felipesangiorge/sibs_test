package com.sibs_test.sibs_test_felipe.core

import androidx.lifecycle.LiveData
import com.sibs_test.sibs_test_felipe.data.network.BaseResponse
import com.squareup.moshi.Types
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiLiveDataCallAdapterFactory() : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {

        val typeObservable = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        if (getRawType(typeObservable) != ApiResponse::class.java) {
            throw IllegalArgumentException("Type is not an resource")
        }
        if (typeObservable !is ParameterizedType) {
            throw IllegalArgumentException("Resource need to be parameterized")
        }
        val bodyType = getParameterUpperBound(0, typeObservable)
        val type = Types.newParameterizedType(BaseResponse::class.java, bodyType)

        return ApiLiveDataCallAdapter<Any>(type, retrofit)
    }
}