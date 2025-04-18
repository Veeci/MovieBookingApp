package com.example.moviebooking

import android.os.Bundle
import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.moviebooking.presentation.auth.SplashRouter
import com.example.moviebooking.presentation.auth.login.LoginRouter
import com.example.moviebooking.presentation.auth.signup.SignUpRouter
import com.example.moviebooking.presentation.main.explore.ExploreRouter
import com.example.moviebooking.presentation.main.explore.pages.series.seriesDetail.SeriesDetailRouter
import com.example.moviebooking.presentation.main.home.HomeRouter
import com.example.moviebooking.presentation.main.home.screens.movieDetail.MovieDetailRouter
import com.example.moviebooking.presentation.main.home.screens.searching.SearchingRouter
import com.example.moviebooking.presentation.main.home.screens.seeAll.SeeAllRouter

class MainNavigator :
    BaseNavigator(),
    MainRouter,
    SplashRouter,
    LoginRouter,
    SignUpRouter,
    HomeRouter,
    ExploreRouter,
    SeeAllRouter,
    MovieDetailRouter,
    SeriesDetailRouter,
    SearchingRouter {
    override fun goToLoginScreen() {
        onNextScreen(
            action = R.id.action_splashFragment_to_loginScreen, extras = null, isFinished = null
        )
    }

    override fun goToSignUpScreen() {
        onNextScreen(
            action = R.id.action_loginScreen_to_signUpScreen, extras = null, isFinished = null
        )
    }

    override fun goToMainScreen() {
        onNextScreen(
            action = R.id.action_loginScreen_to_mainFragment, extras = null, isFinished = null
        )
    }

    override fun goToAuthScreen() {
        onNextScreen(
            action = R.id.action_signUpScreen_to_loginScreen, extras = null, isFinished = null
        )
    }

    override fun goToMovieDetailFromHome(extras: Bundle) {
        onNextScreen(
            action = R.id.action_mainFragment_to_movieDetailScreen,
            extras = extras,
            isFinished = null
        )
    }

    override fun goToSeeAll() {
        onNextScreen(
            action = R.id.action_mainFragment_to_seeAllFragment, extras = null, isFinished = null
        )
    }

    override fun goToSearchingResult(extras: Bundle) {
        onNextScreen(
            action = R.id.action_mainFragment_to_searchingFragment,
            extras = extras,
            isFinished = null
        )
    }

    override fun backToLogin() {
        onNextScreen(
            action = R.id.action_mainFragment_to_loginScreen,
            extras = null,
            isFinished = true
        )
    }

    override fun goToMovieDetailFromSeeAll(extras: Bundle) {
        onNextScreen(
            action = R.id.action_seeAllFragment_to_movieDetailScreen,
            extras = extras,
            isFinished = null
        )
    }

    override fun toHomeScreen() {
        onNextScreen(
            action = R.id.action_movieDetailScreen_to_mainFragment,
            extras = null,
            isFinished = null
        )
    }

    override fun goToMovieDetailFromExplore(extras: Bundle) {
        onNextScreen(
            action = R.id.action_mainFragment_to_movieDetailScreen,
            extras = extras,
            isFinished = null
        )
    }

    override fun goToSeriesDetailScreen(extras: Bundle) {
        onNextScreen(
            action = R.id.action_mainFragment_to_seriesDetailScreen,
            extras = extras,
            isFinished = null
        )
    }

    override fun goToCelebrityDetailScreen(extras: Bundle) {
        onNextScreen(
            action = R.id.action_mainFragment_to_celebrityDetailScreen,
            extras = extras,
            isFinished = null
        )
    }

    override fun goToSeasonDetailScreen(extras: Bundle) {
        onNextScreen(
            action = R.id.action_seriesDetailScreen_to_seasonDetailScreen,
            extras = extras,
            isFinished = null
        )
    }

    override fun goToMovieDetailScreen(extras: Bundle) {
        onNextScreen(
            action = R.id.action_searchingFragment_to_movieDetailScreen,
            extras = extras,
            isFinished = null
        )
    }

    override fun goToSerieDetailScreen(extras: Bundle) {
        onNextScreen(
            action = R.id.action_searchingFragment_to_seriesDetailScreen,
            extras = extras,
            isFinished = null
        )
    }
}