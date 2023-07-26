package com.example.core.utils

import android.annotation.SuppressLint
import android.util.Log

object AppLogger {

    fun log(message: String, tag: String = EXCEPTION_LOG_TAG) =
        logError(LogException(message), null, tag)

    fun log(throwable: Throwable, tag: String = EXCEPTION_LOG_TAG) =
        logError(throwable, null, tag)

    fun log(throwable: Throwable, message: String, tag: String = EXCEPTION_LOG_TAG) =
        logError(throwable, message, tag)


    @SuppressLint("LogNotTimber")
    private fun logError(
        throwable: Throwable?,
        message: String?,
        tag: String
    ) {
        val errorMessage = message ?: throwable?.localizedMessage ?: throwable?.message
        ?: DEFAULT_EXCEPTION_MESSAGE
        Log.e(tag, errorMessage, throwable)
    }

    private class LogException(
        message: String
    ) : Exception(message)

    private const val EXCEPTION_LOG_TAG = "EXCEPTION_LOG_TAG"
    private const val DEFAULT_EXCEPTION_MESSAGE = "Unexpected exception"
}