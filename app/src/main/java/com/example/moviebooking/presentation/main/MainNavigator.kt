package com.example.moviebooking.presentation.main

import com.example.baseproject.presentation.navigation.BaseNavigator

class MainNavigator: BaseNavigator() {
    fun toErrorScreen() {
        onErrorEvent(
            action = 0,
            message = "Unknown error"
        )
    }
}