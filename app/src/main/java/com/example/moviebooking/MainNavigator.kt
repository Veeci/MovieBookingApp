package com.example.moviebooking

import android.os.Bundle
import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.moviebooking.presentation.auth.SplashRouter
import com.example.moviebooking.presentation.auth.login.LoginRouter
import com.example.moviebooking.presentation.auth.signup.SignUpRouter
import com.example.moviebooking.presentation.main.home.HomeRouter
import com.example.moviebooking.presentation.main.home.screens.movieDetail.MovieDetailRouter
import com.example.moviebooking.presentation.main.home.screens.seeAll.SeeAllRouter

class MainNavigator : BaseNavigator(), MainRouter, SplashRouter, LoginRouter, SignUpRouter,
    HomeRouter, SeeAllRouter, MovieDetailRouter {
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

    override fun goToSearchingResult() {
        TODO("Not yet implemented")
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
}