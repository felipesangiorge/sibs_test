package com.sibs_test.sibs_test_felipe.core

import com.sibs_test.sibs_test_felipe.network.BaseResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ApiCallAdapter<R>(
    private val responseType: Type,
    private val retrofit: Retrofit
) : CallAdapter<BaseResponse<R>, ApiResponse<R>> {
    override fun adapt(call: Call<BaseResponse<R>>): ApiResponse<R> {
        return try {
            val response = call.execute()
            ApiResponse.create(response, responseType, retrofit)
        } catch (e: Exception) {
            try {
                ApiResponse.create(e, call, responseType, retrofit)
            } catch (ex: Exception) {
                ApiErrorResponse(ex.toString())
            }
        }
    }

    override fun responseType(): Type {
        return responseType
    }
}