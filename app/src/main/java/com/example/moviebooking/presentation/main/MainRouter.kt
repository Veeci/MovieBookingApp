package com.example.moviebooking.presentation.main

import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.baseproject.presentation.navigation.BaseRouter

interface MainRouter: BaseRouter {
    fun toErrorScreen()
}