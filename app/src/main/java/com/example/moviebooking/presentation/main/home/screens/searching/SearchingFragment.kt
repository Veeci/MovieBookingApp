package com.example.moviebooking.presentation.main.home.screens.searching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSearchingBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.domain.viewmodels.SeriesViewModel
import com.example.moviebooking.presentation.main.home.adapters.MovieSearchingAdapter
import com.example.moviebooking.presentation.main.home.adapters.SeriesSearchingAdapter
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper

class SearchingFragment : BaseFragment<FragmentSearchingBinding, SearchingRouter, MainNavigator>(
    R.layout.fragment_searching
) {
    override val navigator: MainNavigator by journeyViewModel()
    private val movieViewModel by journeyViewModel<MovieViewModel>()
    private val serieViewModel by journeyViewModel<SeriesViewModel>()

    private var searchingQuery = ""

    private lateinit var movieSearchingAdapter: MovieSearchingAdapter
    private lateinit var seriesSearchingAdapter: SeriesSearchingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchingQuery = arguments?.getString("searching_query") ?: ""
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSearchingBinding) {
        fetchData()
        setupUI()
        observer()
    }

    private fun fetchData() {
        movieViewModel.searchMovie(searchingQuery)
        serieViewModel.discoverSeries(searchingQuery)
    }

    private fun setupUI() {
        movieSearchingAdapter = MovieSearchingAdapter {
            navigator.goToMovieDetailScreen(
                extras = Bundle().apply {
                    putString("movieID", it.id.toString())
                }
            )
        }

        seriesSearchingAdapter = SeriesSearchingAdapter {
            navigator.goToSerieDetailScreen(
                extras = Bundle().apply {
                    putString("seriesID", it.id.toString())
                }
            )
        }

        with(binding) {
            movieResultRV.apply {
                setHasFixedSize(true)
                setItemViewCacheSize(20)
                layoutManager = CarouselLayoutManager()
                adapter = movieSearchingAdapter
                CarouselSnapHelper().attachToRecyclerView(movieResultRV)
            }

            seriesResultRV.apply {
                setHasFixedSize(true)
                setItemViewCacheSize(20)
                layoutManager = CarouselLayoutManager()
                adapter = seriesSearchingAdapter
                CarouselSnapHelper().attachToRecyclerView(seriesResultRV)
            }
        }
    }

    private fun observer() {
        movieViewModel.movieSearchingResult.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> {
                    showLoading(isLoading = true, preventClicking = true)
                }
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    movieSearchingAdapter.submitList(status.data.results)
                }
            }
        }

        serieViewModel.discoverList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> {
                    showLoading(isLoading = true, preventClicking = true)
                }
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                }
                is ResponseStatus.Success -> {
                    showLoading(isLoading = false)
                    seriesSearchingAdapter.submitList(status.data.results)
                }
            }
        }
    }
}