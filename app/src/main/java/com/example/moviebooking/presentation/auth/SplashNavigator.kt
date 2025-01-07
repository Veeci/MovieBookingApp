package com.example.moviebooking.presentation.auth

import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.moviebooking.R

class SplashNavigator: BaseNavigator() {
    fun goToLoginScreen() {
        onNextScreen(
            action = R.id.action_splashFragment_to_authScreen,
            extras = null,
            isFinished = null
        )
    }
}