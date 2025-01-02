package com.example.baseproject.domain.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.baseproject.R

class EncryptedSharedPreferences(context: Context) {
    private val pref: SharedPreferences

    init {
        val masterKey =
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

        pref =
            EncryptedSharedPreferences.create(
                context,
                "secret_" + context.resources.getString(R.string.base_project),
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
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
        value: Any?,
    ): Any? {
        return when (value) {
            is String -> pref.getString(key, null)
            is Int -> pref.getInt(key, 0)
            is Boolean -> pref.getBoolean(key, false)
            is Float -> pref.getFloat(key, 0f)
            is Long -> pref.getLong(key, 0L)
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}
