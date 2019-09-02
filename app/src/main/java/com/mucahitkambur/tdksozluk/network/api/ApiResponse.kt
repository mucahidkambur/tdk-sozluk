package com.mucahitkambur.tdksozluk.network.api

import com.google.gson.Gson
import retrofit2.Response
import com.mucahitkambur.tdksozluk.model.Result

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(Result(error.message!!))
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val errorMessage = Gson().fromJson(response.errorBody()?.string(), Result::class.java)
                ApiErrorResponse(errorMessage)
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: Result) : ApiResponse<T>()