package com.olamachia.simpleblogappwithdatabinding.util

import java.lang.Exception

data class DataState<out T>(val status: Status, val data: T?, val error: Exception?, var message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): DataState<T> {
            return DataState(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String, error: Exception?): DataState<Nothing> {
            return DataState(Status.ERROR, null, error, message)
        }

        fun <T> loading(data: T? = null): DataState<T> {
            return DataState(Status.LOADING, data, null, null)
        }
    }
}