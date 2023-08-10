package com.example.mvi_jetpackcompose.core.data.remote

import com.google.gson.Gson
import java.io.IOException
import java.io.InterruptedIOException
import javax.inject.Inject
import retrofit2.HttpException
import retrofit2.Response

class ApiHelper @Inject constructor(
    val gson: Gson
) {

    /**
     * Invokes [networkCall] which doesn't have a specific error response,
     * so [NoError] class is used as error type
     */
    suspend fun <Result> makeApiCall(networkCall: suspend () -> Response<Result>): Result {
        return makeApiCallWithError<Result, NoError> {
            networkCall()
        }
    }

    /**
     * Invokes [networkCall] which can return error with json body,
     * if error type is not [NoError], deserializes json body to [Error] type model and throws
     * [ResponseException]
     * if not, generates [GeneralException] by error code
     */
    suspend inline fun <Result, reified Error : ErrorResponse> makeApiCallWithError(
        crossinline networkCall: suspend () -> Response<Result>
    ): Result {
        val result = apiCall(
            { networkCall() },
            onError = { errorBody ->
                if (Error::class != NoError::class && errorBody.body != null) {
                    val errorResponse = gson.fromJson(errorBody.body, Error::class.java)
                    throw ResponseException(errorResponse)
                }
                throw generateExceptionByErrorCode(errorBody.code)
            }
        )
        return result!!
    }

    /**
     * Tries to call [networkCall] and if it is successful returns result,
     * handles and throws [GeneralException]
     * Invokes [onError] if general exceptions was not caught and result is not successful
     */
    suspend fun <Result> apiCall(
        networkCall: suspend () -> Response<Result>,
        onError: suspend (ErrorBody) -> Unit
    ): Result? {
        val result = try {
            networkCall()
        } catch (e: HttpException) {
            throw GeneralException.NoInternetException
        } catch (e: IOException) {
            throw GeneralException.NoInternetException
        } catch (e: InterruptedIOException) {
            throw GeneralException.TimeOutException
        }

        if (result.isSuccessful) {
            return result.body()!!
        }
        onError(ErrorBody(result.code(), result.errorBody()?.string()))
        return null
    }

    data class ErrorBody(val code: Int, val body: String?)
}
