package com.example.moviebooking

import com.example.baseproject.BaseApplication
import com.example.moviebooking.domain.common.di.ServiceModule
import org.koin.core.module.Module
import org.koin.dsl.module

class MainApplication: BaseApplication() {
    override fun appModule() = module {
        includes(
            super.appModule(),
            ServiceModule.init(),
        )
    }
}