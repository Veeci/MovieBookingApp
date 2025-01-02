package com.example.baseproject.domain.utils

import android.content.Context

enum class ThemeMode {
    LIGHT,
    DARK,
    CUSTOM,
}

class ThemeManager(context: Context) {
    private val encryptedSharedPreferences = EncryptedSharedPreferences(context)

    var currentTheme: ThemeMode
        set(value) = encryptedSharedPreferences.setData("theme", value.name)

        get() {
            val themeName = encryptedSharedPreferences.getData("theme", ThemeMode.LIGHT.name) as? String
            return themeName?.let { ThemeMode.valueOf(it) } ?: ThemeMode.LIGHT
        }
}
