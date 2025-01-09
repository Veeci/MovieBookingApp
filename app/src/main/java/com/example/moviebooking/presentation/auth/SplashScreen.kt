package com.example.moviebooking.presentation.auth

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.domain.utils.screenViewModel
import com.example.baseproject.domain.utils.toastShort
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSplashBinding
import com.example.moviebooking.domain.usecases.movies.MovieViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseFragment<FragmentSplashBinding, SplashRouter, MainNavigator>(
    R.layout.fragment_splash
) {
    override val navigator: MainNavigator by navigatorViewModel()
    override val viewModel: MovieViewModel by screenViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSplashBinding) {
        initialize()
        observe()
    }

    private fun initialize() {
        viewModel.fetchAllMovies()

        binding.testbtn.safeClick {
            router?.goToLoginScreen()
        }
    }

    private fun observe() {
        viewModel.allMoviesFetchState.observe(this) { status ->
            when(status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error ->
                    showLoading(isLoading = false)
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
//                    router?.goToLoginScreen()
                }
            }
        }
    }
}