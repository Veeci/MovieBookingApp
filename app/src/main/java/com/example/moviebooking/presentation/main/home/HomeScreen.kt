package com.example.moviebooking.presentation.main.home

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentHomeScreenBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.home.adapters.NowPlayingAdapter
import com.example.moviebooking.presentation.main.home.adapters.UpcomingAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper


class HomeScreen : BaseFragment<FragmentHomeScreenBinding, HomeRouter, MainNavigator>(
    R.layout.fragment_home_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewModel: MovieViewModel by journeyViewModel()

    private val nowPlayingAdapter = NowPlayingAdapter()
    private val upcomingAdapter = UpcomingAdapter()

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
            val welcomeMessages: Array<String> = resources.getStringArray(R.array.welcome_messages)
            val randomIndex = (welcomeMessages.indices).random()
            welcomeText.text = welcomeMessages[randomIndex]

            nowPlayingCarousel.apply {
                setHasFixedSize(true)
                layoutManager = CarouselLayoutManager()
                adapter = nowPlayingAdapter
                CarouselSnapHelper().attachToRecyclerView(nowPlayingCarousel)
            }

            upcomingRV.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = upcomingAdapter
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

        movieViewModel.upcomingList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> {

                }

                is ResponseStatus.Error -> {

                }

                is ResponseStatus.Success -> {
                    upcomingAdapter.submitList(status.data.results)
                }
            }
        }
    }
}