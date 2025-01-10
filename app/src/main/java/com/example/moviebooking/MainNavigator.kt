package com.example.moviebooking

import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.moviebooking.presentation.auth.login.LoginRouter
import com.example.moviebooking.presentation.auth.signup.SignUpRouter
import com.example.moviebooking.presentation.auth.SplashRouter

class MainNavigator :
    BaseNavigator(),
    MainRouter,
    SplashRouter,
    LoginRouter,
    SignUpRouter {
    override fun goToLoginScreen() {
        onNextScreen(
            action = R.id.action_splashFragment_to_loginScreen,
            extras = null,
            isFinished = null
        )
    }

    override fun goToSignUpScreen() {
        onNextScreen(
            action = R.id.action_loginScreen_to_signUpScreen,
            extras = null,
            isFinished = null
        )
    }

    override fun goToMainScreen() {
        onNextScreen(
            action = R.id.action_loginScreen_to_mainFragment,
            extras = null,
            isFinished = null
        )
    }

    override fun goToAuthScreen() {
        onNextScreen(
            action = R.id.action_signUpScreen_to_loginScreen,
            extras = null,
            isFinished = null
        )
    }
}