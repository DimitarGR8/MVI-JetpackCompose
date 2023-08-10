package com.example.mvi_jetpackcompose.core.data.remote

/**
 * Abstract Network Error Response type
 */
abstract class ErrorResponse {
    open var errorCode: Int? = null
}

/**
 * Any error that has not been handled
 */
class UnknownError(override var errorCode: Int?) : ErrorResponse()

/**
 * Used for calls that does not have specific error response type
 */
object NoError : ErrorResponse()
