package com.example.core.utils

fun Double?.isNotNull(): Boolean = this != null
fun Long?.isNotNull(): Boolean = this != null
fun Int?.isNotNull(): Boolean = this != null

fun Double.isPositive() = this > 0.0
fun Double.isNegative() = this < 0.0
fun Double.isNotNegative() = this >= 0.0
fun Double.isNotPositive() = this <= 0.0


fun Long.isPositive() = this > 0.0
fun Long.isNegative() = this < 0.0
fun Long.isNotNegative() = this >= 0.0
fun Long.isNotPositive() = this <= 0.0

fun Int.isPositive() = this >= 0
fun Int.isNegative() = this <= 0
fun Int.isNotNegative() = this >= 0
fun Int.isNotPositive() = this <= 0