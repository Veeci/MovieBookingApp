package com.example.moviebooking.presentation.main.explore

import android.os.Bundle
import com.example.baseproject.presentation.navigation.BaseRouter

interface ExploreRouter: BaseRouter {
    fun goToMovieDetailFromExplore(extras: Bundle)

    fun goToSeriesDetailScreen(extras: Bundle)

    fun goToCelebrityDetailScreen(extras: Bundle)
}