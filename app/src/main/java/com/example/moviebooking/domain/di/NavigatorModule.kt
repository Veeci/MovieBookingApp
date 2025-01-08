package com.example.moviebooking.domain.di

import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.presentation.auth.LoginRouter
import com.example.moviebooking.presentation.auth.SignUpRouter
import com.example.moviebooking.presentation.auth.SplashRouter
import org.koin.dsl.module

object NavigatorModule {
    fun init() = module {
        navigatorViewModel { MainNavigator() }
    }
}