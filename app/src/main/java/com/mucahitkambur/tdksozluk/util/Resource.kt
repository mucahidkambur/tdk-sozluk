package com.mucahitkambur.tdksozluk.util

import com.mucahitkambur.tdksozluk.model.Result

data class Resource<out T>(val status: Status, val data: T?, val message: Result?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: Result, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}