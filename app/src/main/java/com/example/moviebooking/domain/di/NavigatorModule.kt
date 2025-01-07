package com.example.moviebooking.domain.di

import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.moviebooking.presentation.auth.LoginNavigator
import com.example.moviebooking.presentation.auth.SignUpNavigator
import com.example.moviebooking.presentation.auth.SplashNavigator
import org.koin.dsl.module

object NavigatorModule {
    fun init() = module {
        navigatorViewModel { SplashNavigator() }
        navigatorViewModel { SignUpNavigator() }
        navigatorViewModel { LoginNavigator() }
    }
}