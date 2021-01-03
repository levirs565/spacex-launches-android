package com.levirs.spacexlaunches.domain.util

data class ResultState<T>(val data: T?, val error: String?) {
    companion object {
        fun <T> succes(data: T) = ResultState(data, null)
        fun <T> loading() = ResultState<T>(null, null)
        fun <T> error(error: String) = ResultState<T>(null, error)
    }

    fun isLoading() = data == null && error == null
    fun isError() = data == null && error != null
    fun isSuccess() = data != null && error == null
}
