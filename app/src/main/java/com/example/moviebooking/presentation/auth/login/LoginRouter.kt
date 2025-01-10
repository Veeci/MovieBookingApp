package com.example.moviebooking.presentation.auth.login

import com.example.baseproject.presentation.navigation.BaseRouter

interface LoginRouter: BaseRouter {
    fun goToSignUpScreen()

    fun goToMainScreen()
}