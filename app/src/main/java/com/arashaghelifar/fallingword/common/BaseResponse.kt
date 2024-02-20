package com.arashaghelifar.fallingword.common

sealed class BaseResponse<out T> {
    data class Success<out T>(val data: T) : BaseResponse<T>()
    data class Error(val error: String) : BaseResponse<Nothing>()
    object Loading : BaseResponse<Nothing>()
}