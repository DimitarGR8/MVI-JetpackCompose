package com.example.mvi_jetpackcompose.core.data.remote

/**
 * General Exceptions that can happen inside application
 */
sealed class GeneralException : Exception() {
    object NoInternetException : GeneralException()
    object TimeOutException : GeneralException()
    object UnauthorizedException : GeneralException()
    data class UnknownException(var code: Int) : GeneralException()
}

/**
 * Specific error wrapper exception
 */
open class ResponseException(val error: ErrorResponse) : Exception()

fun generateExceptionByErrorCode(code: Int): Exception {
    return when (code) {
        401 -> GeneralException.UnauthorizedException
        else -> GeneralException.UnknownException(code = code)
    }
}
