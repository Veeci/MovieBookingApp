package com.example.moviebooking.domain.di

import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.moviebooking.MainNavigator
import org.koin.dsl.module

object NavigatorModule {
    fun init() = module {
        navigatorViewModel { MainNavigator() }
    }
}