package com.example.core.utils

import android.text.TextUtils
import android.util.Patterns

fun isValidEmail(target: CharSequence?): Boolean {
    return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target!!).matches()
}

fun isValidEmail(target: String): Boolean {
    return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
}

fun isPhoneCorrectLength(phone: String?): Boolean {
    if (phone.isNullOrBlank()) return false
    if (phone.length > 5) {
        if (phone.substring(0, 4) != "+380") {
            return false
        }
    }
    return phone.length == 13
}
