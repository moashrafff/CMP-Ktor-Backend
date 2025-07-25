package org.moashrafff.showcase.data.network

sealed class ResultWrapper<out T>() {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val exception: Exception) : ResultWrapper<Nothing>()
    data object InProgress : ResultWrapper<Nothing>()
}