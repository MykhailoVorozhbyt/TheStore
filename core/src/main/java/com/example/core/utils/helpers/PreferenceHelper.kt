package com.example.core.utils.helpers

import android.content.SharedPreferences
import com.example.core.domain.constants.Constants
import com.example.core.utils.elvis
import com.example.core.utils.enums.PreferenceKey
import javax.inject.Inject

class PreferenceHelper @Inject constructor(private val preference: SharedPreferences) {

    fun getDataByKey(key: PreferenceKey, defaultValue: String = Constants.EMPTY_STRING): String =
        preference.getString(key.name, defaultValue).elvis()

    fun getDataByKey(key: PreferenceKey, defaultValue: Boolean = false): Boolean =
        preference.getBoolean(key.name, defaultValue)

    fun getDataByKey(key: PreferenceKey, defaultValue: Long = 0L): Long =
        preference.getLong(key.name, defaultValue)

    fun setDataByKey(key: PreferenceKey, value: String) {
        preference.edit().putString(key.name, value).commit()
    }

    fun setDataByKey(key: PreferenceKey, value: Boolean) {
        preference.edit().putBoolean(key.name, value).commit()
    }

    fun setDataByKey(key: PreferenceKey, value: Long) {
        preference.edit().putLong(key.name, value).commit()
    }
}
