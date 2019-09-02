package com.mucahitkambur.tdksozluk.network.api

import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import com.mucahitkambur.tdksozluk.model.Result
import com.mucahitkambur.tdksozluk.util.ERROR_MESSAGE
import java.lang.IllegalStateException

class ApiException {

    companion object {
        fun create(error: Exception): Result = when(error) {
            is UnknownHostException -> Result("Lütfen internet bağlantınızı kontrol ediniz.")
            is SocketTimeoutException -> Result("İstek zaman aşımına uğradı, lütfen internet bağlantınızı kontrol edip, tekrar deneyiniz.")
            is SocketException -> Result("İstek sonuçlanmadı, lütfen internet bağlantınızı kontrol edip, tekrar deneyiniz.")
            is IllegalStateException -> Result("Sonuç bulunamadı.")
            else -> ERROR_MESSAGE
        }
    }
}