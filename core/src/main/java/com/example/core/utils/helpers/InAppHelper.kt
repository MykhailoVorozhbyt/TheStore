package com.example.core.utils.helpers

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun showMessage(context: Context, @StringRes messageId: Int?) {
    messageId?.let { showMessage(context, context.getString(messageId)) }
}

fun showMessage(context: Context, message: String?) {
    if (message.isNullOrBlank()) {
        return
    }
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

