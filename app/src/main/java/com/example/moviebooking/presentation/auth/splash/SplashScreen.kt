package com.example.moviebooking.presentation.auth.splash

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.screenViewModel
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSplashScreenBinding
import com.example.moviebooking.domain.usecases.movies.MovieViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseFragment<FragmentSplashScreenBinding, SplashNavigator>(
    R.layout.fragment_splash_screen
) {
    override val navigator: SplashNavigator by navigatorViewModel()
    override val viewModel: MovieViewModel by screenViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSplashScreenBinding) {
        initialize()
        observe()
    }

    private fun initialize() {
        viewModel.getNowPlayingMovies()
    }

    private fun observe() {

    }
}