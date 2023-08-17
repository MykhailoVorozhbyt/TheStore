package com.example.core.utils

import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.core.domain.constants.Constants

fun String?.elvis(): String = this ?: Constants.EMPTY_STRING
fun String?.elvis(value: String): String = this ?: value

fun Int?.elvis(): Int = this ?: 0
fun Int?.elvis(value: Int): Int = this ?: value

fun Double?.elvis(): Double = this ?: 0.0
fun Double?.elvis(value: Double): Double = this ?: value

fun Float?.elvis(): Float = this ?: 0.0F
fun Float?.elvis(value: Float): Float = this ?: value

fun Long?.elvis(): Long = this ?: 0L
fun Long?.elvis(value: Long): Long = this ?: value

fun Boolean?.elvis(): Boolean = this ?: false

fun Bundle?.elvis(): Bundle = this ?: bundleOf()

fun <T> List<T>?.elvis(): List<T> = this ?: listOf()

@JvmName("elvisT")
fun <T> MutableList<T>?.elvis(): MutableList<T> = this ?: mutableListOf()
