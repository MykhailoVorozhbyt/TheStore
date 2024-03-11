package com.example.core.utils.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T : Any> T.toGson(): String {
    val type = object : TypeToken<T>() {}.type
    return Gson().toJson(this, type)
}

inline fun <reified T : Any> String.fromGson(): T {
    val type = object : TypeToken<T>() {}.type
    return Gson().fromJson(this, type)
}

inline fun <reified T : Any> T.json(): String = Gson().toJson(this, T::class.java)
inline fun <reified T : Any> String.fromJson(): T = Gson().fromJson(this, T::class.java)

