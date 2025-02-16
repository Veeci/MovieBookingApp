package com.example.moviebooking

import com.example.baseproject.BaseApplication
import com.example.baseproject.domain.utils.LogUtils
import com.example.moviebooking.domain.di.DatabaseModule
import com.example.moviebooking.domain.di.NavigatorModule
import com.example.moviebooking.domain.di.ServiceModule
import com.example.moviebooking.domain.di.UseCaseModule
import com.example.moviebooking.domain.di.ViewModelModule
import org.koin.core.module.Module
import org.koin.dsl.module

class MainApplication: BaseApplication() {
    override fun appModule() = module {
        includes(
            super.appModule(),
            DatabaseModule.init(),
            ServiceModule.init(),
            NavigatorModule.init(),
            UseCaseModule.init(),
            ViewModelModule.init(),
        )
    }

    override fun onCreate() {
        super.onCreate()

        LogUtils.log("", "")
    }
}