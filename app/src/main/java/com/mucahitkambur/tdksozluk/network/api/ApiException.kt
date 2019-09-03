package com.mucahitkambur.tdksozluk.network.api

import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import com.mucahitkambur.tdksozluk.model.Result
import com.mucahitkambur.tdksozluk.util.ERROR_MESSAGE
import com.mucahitkambur.tdksozluk.util.NO_CONNECTION
import com.mucahitkambur.tdksozluk.util.NO_RESULT
import java.lang.IllegalStateException

class ApiException {

    companion object {
        fun create(error: Exception): Result = when(error) {
            is UnknownHostException -> Result(NO_CONNECTION)
            is SocketTimeoutException -> Result(NO_CONNECTION)
            is SocketException -> Result(NO_CONNECTION)
            is IllegalStateException -> Result(NO_RESULT)
            else -> Result(ERROR_MESSAGE)
        }
    }
}