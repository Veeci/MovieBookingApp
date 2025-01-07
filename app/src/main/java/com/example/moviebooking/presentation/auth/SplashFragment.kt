package com.example.moviebooking.presentation.auth

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.screenViewModel
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSplashBinding
import com.example.moviebooking.domain.usecases.movies.MovieViewModel

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashNavigator>(
    R.layout.fragment_splash
) {
    override val navigator: SplashNavigator by navigatorViewModel()
    override val viewModel: MovieViewModel by screenViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSplashBinding) {
        initialize()
        observe()
    }

    private fun initialize() {
        viewModel.fetchAllMovies()
    }

    private fun observe() {
        viewModel.allMoviesFetchState.observe(this) { status ->
            when(status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true)
                is ResponseStatus.Error ->
                    showLoading(isLoading = false)
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    navigator.goToLoginScreen()
                }
            }
        }
    }
}