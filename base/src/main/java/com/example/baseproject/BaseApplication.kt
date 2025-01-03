package com.example.baseproject

import android.app.Application
import com.example.baseproject.domain.utils.EncryptedSharedPreferences
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.baseproject.presentation.navigation.BaseNavigator
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

abstract class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(appModule())
        }
    }

    open fun appModule() =
        module {
            factory<BaseNavigator> { BaseNavigator() }
            factory<BaseViewModel> { BaseViewModel() }
            factory<EncryptedSharedPreferences> { EncryptedSharedPreferences(get()) }
        }
}
