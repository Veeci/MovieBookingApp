package com.example.moviebooking.presentation.auth

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.message
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.positiveAction
import com.example.baseproject.domain.utils.screenViewModel
import com.example.baseproject.domain.utils.simpleAlert
import com.example.baseproject.domain.utils.title
import com.example.baseproject.domain.utils.toastShort
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSplashBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseFragment<FragmentSplashBinding, SplashRouter, MainNavigator>(
    R.layout.fragment_splash
) {
    override val navigator: MainNavigator by navigatorViewModel()
    override val viewModel: MovieViewModel by journeyViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSplashBinding) {
        fetchData()
        setup()
        observe()
    }

    private fun fetchData() {
//        viewModel.fetchAllMovies()
        viewModel.getNowPlayingMovies()
    }

    private fun setup() {
        activity.installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.allMoviesFetchState.value !is ResponseStatus.Success
            }

            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    "scaleX",
                    0.4f,
                    0.0f
                )

                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 1000L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    "scaleY",
                    0.4f,
                    0.0f
                )

                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 1000L
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()
            }
        }
    }

    private fun observe() {
//        viewModel.allMoviesFetchState.observe(this) { status ->
//            when (status) {
//                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
//                is ResponseStatus.Error -> {
//                    showLoading(isLoading = false)
//                    activity.simpleAlert {
//                        title(getString(R.string.error))
//                        message(getString(R.string.startup_error))
//                        positiveAction(name = getString(R.string.ok)) {
//                            activity.finish()
//                        }
//                    }
//                }
//                is ResponseStatus.Success -> {
//                    showLoading(isLoading = false)
//                    viewModel.saveNowPlayingMovies()
////                    router?.goToLoginScreen()
//                }
//            }
//        }

        viewModel.nowPLayingList.observe(this) { status ->
            when(status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                    activity.simpleAlert {
                        title(getString(R.string.error))
                        message(getString(R.string.startup_error))
                        positiveAction(name = getString(R.string.ok)) {
                            activity.finish()
                        }
                    }
                }

                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    activity.toastShort("Fetched successfully! Saving data")
                    viewModel.saveNowPlayingMovies(status.data.toMovieItemEntities())
                }
            }
        }

        viewModel.nowPlayingSavingResult.observe(this) { status ->
            if(status == true) {
                activity.toastShort("Data saved successfully")
                router?.goToLoginScreen()
//                viewModel.retrieveLocalData()
            } else {
                activity.toastShort("Error saving now playing movies")
            }
        }
    }
}