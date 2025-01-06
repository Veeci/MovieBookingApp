package com.example.moviebooking.presentation.auth.account

import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.moviebooking.R

class SignUpNavigator: BaseNavigator() {
    fun goToAuthScreen() {
        onNextScreen(
            action = R.id.action_signUpScreen_to_authScreen,
            extras = null,
            isFinished = null
        )
    }
}