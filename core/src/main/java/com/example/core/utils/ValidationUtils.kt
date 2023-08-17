package com.example.core.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

fun isValidEmail(target: CharSequence?): Boolean {
    return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target!!).matches()
}

fun isValidEmail(target: String): Boolean {
    return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
}

fun isUaPhoneCorrect(phone: String?): Boolean {
    val ukrainianPhoneNumberPattern = "^\\+380\\d{2}\\d{7}$"
    val pattern = Pattern.compile(ukrainianPhoneNumberPattern)
    if (phone.isNullOrBlank()) return false

    return phone.length == 13
}

fun isValidUkrainianPhoneNumber(phoneNumber: String): Boolean {
    val ukrainianPhoneNumberPattern = "^\\+380\\d{2}\\d{7}$"
    val pattern = Pattern.compile(ukrainianPhoneNumberPattern)
    val matcher = pattern.matcher(phoneNumber)
    return matcher.matches()
}
