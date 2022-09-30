package com.example.ionixtest.data.networkUtils

import android.util.Log
import com.example.ionixtest.commons.orAlternative
import java.io.IOException
import retrofit2.HttpException

object HandlerResultHelper {

    suspend fun <T> getResult(request: suspend () -> T): RequestStatus<T> {
        return try {
            RequestStatus.Success(request.invoke())
        } catch (e: Throwable) {
            when (e) {
                is IOException -> RequestStatus.Error(null, "revisa tu conexion a internet")
                is HttpException -> {
                    Log.e("HandlerResultHelper", "getResult: ${e.message}")
                    RequestStatus.Error(
                        e.code(),
                        e.message().orAlternative("HttpException -> ${e.code()}")
                    )
                }
                else -> {
                    Log.e("HandlerResultHelper", "getResult: ${e.message}")
                    RequestStatus.Error(null, e.message.orAlternative("error desconocido"))
                }
            }
        }
    }
}