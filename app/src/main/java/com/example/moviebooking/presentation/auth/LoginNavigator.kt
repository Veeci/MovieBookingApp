package com.example.moviebooking.presentation.auth

import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.moviebooking.R

class LoginNavigator: BaseNavigator() {
    fun goToSignUpScreen() {
        onNextScreen(
            action = R.id.action_authScreen_to_signUpScreen,
            extras = null,
            isFinished = null
        )
    }

    fun goToMainScreen() {
        onNextScreen(
            action = R.id.action_authScreen_to_mainFragment,
            extras = null,
            isFinished = null
        )
    }
}