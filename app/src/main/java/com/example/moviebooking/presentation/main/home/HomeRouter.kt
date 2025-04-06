package com.example.moviebooking.presentation.main.home

import android.os.Bundle
import com.example.baseproject.presentation.navigation.BaseRouter

interface HomeRouter: BaseRouter {
    fun goToMovieDetailFromHome(extras: Bundle)

    fun goToSeeAll()

    fun goToSearchingResult()

    fun backToLogin()
}