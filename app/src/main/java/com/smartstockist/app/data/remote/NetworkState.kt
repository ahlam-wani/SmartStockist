package com.smartstockist.app.data.remote

sealed class NetworkState<out T> {
    class Loading<out T> : NetworkState<T>()
    data class Success<out T>(val data: T?, val message: String? = null, val code: Int? = null) : NetworkState<T>()
    data class Failure<out T>(val throwable: String?) : NetworkState<T>()
    data class Error<out T>(
        val message: String,
        val errorCode: String = "",
        val isSessionExpired: Boolean = false
    ) : NetworkState<T>()
}