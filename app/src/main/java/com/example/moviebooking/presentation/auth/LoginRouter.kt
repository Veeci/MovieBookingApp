package com.example.moviebooking.presentation.auth

import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.baseproject.presentation.navigation.BaseRouter
import com.example.moviebooking.R

interface LoginRouter: BaseRouter {
    fun goToSignUpScreen()

    fun goToMainScreen()
}