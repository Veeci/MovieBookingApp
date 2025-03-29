package com.example.moviebooking.presentation.main.explore.pages.series.seriesDetail

import android.os.Bundle
import com.example.baseproject.presentation.navigation.BaseRouter

interface SeriesDetailRouter : BaseRouter {
    fun goToSeasonDetailScreen(extras: Bundle)
}