package com.example.baseproject.domain.utils

import android.content.Context
import android.content.SharedPreferences
import kotlin.collections.remove

class SharedPreferencesHelper(context: Context) {
    private val pref: SharedPreferences

    init {
        pref =
            context.getSharedPreferences(
                "app_preferences",
                Context.MODE_PRIVATE,
            )
    }

    fun setData(
        key: String,
        value: Any?,
    ) {
        when (value) {
            is String -> pref.edit().putString(key, value).apply()
            is Int -> pref.edit().putInt(key, value).apply()
            is Boolean -> pref.edit().putBoolean(key, value).apply()
            is Float -> pref.edit().putFloat(key, value).apply()
            is Long -> pref.edit().putLong(key, value).apply()
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    fun getData(
        key: String,
        defaultValue: Any?,
    ): Any? {
        return when (defaultValue) {
            is String -> pref.getString(key, null)
            is Int -> pref.getInt(key, 0)
            is Boolean -> pref.getBoolean(key, false)
            is Float -> pref.getFloat(key, 0f)
            is Long -> pref.getLong(key, 0L)
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    fun removeData(key: String) {
        pref.edit().remove(key).apply()
    }
}
