package com.example.moviebooking.presentation.main.home.screens.seeAll

import android.os.Bundle
import com.example.baseproject.presentation.navigation.BaseRouter

interface SeeAllRouter: BaseRouter {
    fun goToMovieDetailFromSeeAll(extras: Bundle)
}