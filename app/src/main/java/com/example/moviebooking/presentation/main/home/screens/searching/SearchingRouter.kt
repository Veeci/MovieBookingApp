package com.example.moviebooking.presentation.main.home.screens.searching

import android.os.Bundle
import com.example.baseproject.presentation.navigation.BaseRouter

interface SearchingRouter : BaseRouter {
    fun goToMovieDetailScreen(extras: Bundle)

    fun goToSerieDetailScreen(extras: Bundle)
}