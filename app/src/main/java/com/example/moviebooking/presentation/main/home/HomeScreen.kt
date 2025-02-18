package com.example.moviebooking.presentation.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.screenViewModel
import com.example.baseproject.domain.utils.toastShort
import com.example.baseproject.domain.viewmodel.BaseViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentHomeScreenBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.home.adapters.NowPlayingAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper


class HomeScreen : BaseFragment<FragmentHomeScreenBinding, HomeRouter, MainNavigator>(
    R.layout.fragment_home_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewModel: MovieViewModel by journeyViewModel()

    private val nowPlayingAdapter = NowPlayingAdapter()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentHomeScreenBinding) {
        initialize()
        setup()
        observe()
    }

    private fun initialize() {
        movieViewModel.getLocalData()
    }

    private fun setup() {
        with(binding) {
            nowPlayingCarousel.apply {
                setHasFixedSize(true)
                layoutManager = CarouselLayoutManager()
                adapter = nowPlayingAdapter
                CarouselSnapHelper().attachToRecyclerView(nowPlayingCarousel)
            }
        }
    }

    private fun observe() {
        movieViewModel.nowPLayingList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> {

                }
                is ResponseStatus.Error -> {

                }
                is ResponseStatus.Success -> {
                    nowPlayingAdapter.submitList(status.data.results)
                }
            }
        }
    }
}