package com.levirs.spacexlaunches.domain.util

data class ResultState<T>(val data: T?, val error: T?) {
    fun isLoading() = data == null && error == null
    fun isError() = data == null && error != null
    fun isSuccess() = data != null && error == null
}
