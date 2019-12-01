package com.test.mvvm.model.api

sealed class ApiResult<T> {
    companion object {
        fun <T> loading(): ApiResult<T> {
            return Loading()
        }

        fun <T> loaded(): ApiResult<T> {
            return Loaded()
        }

        fun <T> error(throwable: Throwable): ApiResult<T> {
            return Error(throwable)
        }

        fun <T> success(result: T?): ApiResult<T> {
            return when (result) {
                null -> Empty()
                else -> Success(result)
            }
        }
    }

    data class Success<T>(val result: T) : ApiResult<T>()

    data class Error<T>(val throwable: Throwable) : ApiResult<T>()

    class Empty<T> : ApiResult<T>()

    class Loading<T> : ApiResult<T>()

    class Loaded<T> : ApiResult<T>()
}
