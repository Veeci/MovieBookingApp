package com.example.moviebooking.presentation.auth

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.MutableLiveData
import com.example.baseproject.domain.utils.LogUtils
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.message
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.negativeAction
import com.example.baseproject.domain.utils.positiveAction
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.domain.utils.screenViewModel
import com.example.baseproject.domain.utils.simpleAlert
import com.example.baseproject.domain.utils.title
import com.example.baseproject.domain.utils.toastShort
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSplashBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.domain.viewmodels.PeopleViewModel
import com.example.moviebooking.domain.viewmodels.SeriesViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseFragment<FragmentSplashBinding, SplashRouter, MainNavigator>(
    R.layout.fragment_splash
) {
    override val navigator: MainNavigator by navigatorViewModel()

    private val movieViewModel by journeyViewModel<MovieViewModel>()
    private val seriesViewModel by journeyViewModel<SeriesViewModel>()
    private val peopleViewModel by journeyViewModel<PeopleViewModel>()

    private var isDataReady: MutableLiveData<Map<String, Boolean>> = MutableLiveData()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSplashBinding) {
        fetchData()
        setup()
        observe()
    }

    private fun fetchData() {
        movieViewModel.fetchAllMovies()
        movieViewModel.fetchGenreList()

        seriesViewModel.fetchAllSeries()
        seriesViewModel.fetchGenreList()

        peopleViewModel.fetchPeopleList()
    }

    private fun setup() {
        activity.installSplashScreen().apply {
            setKeepOnScreenCondition {
                movieViewModel.allMoviesFetchState.value !is ResponseStatus.Success
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
        movieViewModel.allMoviesFetchState.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                    activity.simpleAlert {
                        title(getString(R.string.error))
                        message(getString(R.string.startup_error))
                        positiveAction(name = getString(R.string.ok)) {
                            activity.finish()
                        }
                        negativeAction(name = getString(R.string.cancel)) {
                            dismiss()
                        }
                    }
                }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    isDataReady.postValue(mapOf("movies" to true))
                }
            }
        }

        movieViewModel.genreList.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                    activity.simpleAlert {
                        title(getString(R.string.error))
                        message(getString(R.string.startup_error))
                        positiveAction(name = getString(R.string.ok)) {
                            activity.finish()
                        }
                        negativeAction(name = getString(R.string.cancel)) {
                            dismiss()
                        }
                    }
                }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    isDataReady.postValue(mapOf("movies_genre" to true))
                }
            }
        }

        seriesViewModel.fetchAllState.observe(viewLifecycleOwner) { status ->
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
                        negativeAction(name = getString(R.string.cancel)) {
                            dismiss()
                        }
                    }
                }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    isDataReady.postValue(mapOf("series" to true))
                }
            }
        }

        seriesViewModel.seriesGenreList.observe(viewLifecycleOwner) { status ->
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
                        negativeAction(name = getString(R.string.cancel)) {
                            dismiss()
                        }
                    }
                }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    isDataReady.postValue(mapOf("series_genres" to true))
                }
            }
        }

        peopleViewModel.popularPeopleList.observe(viewLifecycleOwner) { status ->
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
                        negativeAction(name = getString(R.string.cancel)) {
                            dismiss()
                        }
                    }
                }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    LogUtils.log("PeopleData", "People data ready")
                    isDataReady.postValue(mapOf("people" to true))
                }
            }
        }

        isDataReady.observe(viewLifecycleOwner) { state ->
            if(state.values.all { it }) {
                router?.goToLoginScreen()
            }
        }
    }
}