package com.example.composegenapp.common

import com.example.spacex.data.common.DataSourceException

sealed class ResponseResult<out T> {
    data class Success<out T>(val response: T) : ResponseResult<T>()
    data class Error(val exception: String) : ResponseResult<Nothing>()
    object Loading : ResponseResult<Nothing>()
}

inline fun <T : Any> ResponseResult<T>.onSuccess(action: (T) -> Unit): ResponseResult<T> {
    if (this is ResponseResult.Success) action(response)
    return this
}

inline fun <T : Any> ResponseResult<T>.onError(action: (String) -> Unit): ResponseResult<T> {
    if (this is ResponseResult.Error) action(exception)
    return this
}

inline fun <T : Any> ResponseResult<T>.onLoading(action: () -> Unit): ResponseResult<T> {
    if (this is ResponseResult.Loading) action()
    return this
}
