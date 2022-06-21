package com.sibs_test.sibs_test_felipe.data.network

import androidx.lifecycle.LiveData
import com.sibs_test.sibs_test_felipe.core.ApiResponse
import com.sibs_test.sibs_test_felipe.data.network.model_result.BookResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRequest {

    companion object {
        const val DEFAULT_QUERY = "mobile development"
        const val MAX_RESULTS = 20
        const val START_INDEX = 0
    }

    interface BookStoreApi {
        @GET("volumes")
        fun getBookList(
            @Query("q") query: String = DEFAULT_QUERY,
            @Query("maxResults") maxResults: Int = MAX_RESULTS,
            @Query("startIndex") startIndex: Int = START_INDEX
        ): LiveData<ApiResponse<List<BookResult>>>
    }
}