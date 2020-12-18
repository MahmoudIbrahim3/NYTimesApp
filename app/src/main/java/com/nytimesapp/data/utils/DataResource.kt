package com.nytimesapp.data.utils

sealed class DataResource<out T> {

    data class Success<out T>(val value: T): DataResource<T>()

    object Loading: DataResource<Nothing>()

    data class Failure(val errorEntity: ErrorEntity): DataResource<Nothing>()
}