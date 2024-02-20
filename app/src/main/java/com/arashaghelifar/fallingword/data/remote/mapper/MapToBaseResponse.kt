package com.arashaghelifar.fallingword.data.remote.mapper

import com.arashaghelifar.fallingword.common.BaseResponse
import retrofit2.Response

object MapToBaseResponse {
    fun <T, R> Response<T>.mapToBaseResponse(mapper: (T) -> R): BaseResponse<R> {

        if (isSuccessful && this.body() != null) {
            val result = body()?.let { mapper(it) }
            result?.let {
                return BaseResponse.Success(it)
            }
        }

        return BaseResponse.Error(error = this.errorBody()?.toString() ?: "Network Error")
    }
}