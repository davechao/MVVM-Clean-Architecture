package com.test.mvvm.model.api

import java.lang.Exception

sealed class Result<T> {
    companion object {
        fun <T> loading(): Result<T> {
            return Loading()
        }

        fun <T> loaded(): Result<T> {
            return Loaded()
        }

        fun <T> error(throwable: Throwable): Result<T> {
            return Error(throwable.message ?: "unknown error")
        }

        fun <T> success(result: T): Result<T> {
            return when (result) {
                null -> Empty()
                else -> Success(result)
            }
        }
    }
}

data class Success<T>(val result: T) : Result<T>()

data class Error<T>(val errorMessage: String) : Result<T>()

class Empty<T> : Result<T>()

class Loading<T> : Result<T>()

class Loaded<T> : Result<T>()