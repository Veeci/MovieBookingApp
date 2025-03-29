package com.example.moviebooking.presentation.main.explore.pages.series

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.Genres
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesItem
import com.example.moviebooking.databinding.FragmentSeriesExploreBinding
import com.example.moviebooking.domain.viewmodels.SeriesViewModel
import com.example.moviebooking.presentation.main.explore.ExploreRouter
import com.example.moviebooking.presentation.main.home.adapters.SeriesAdapter


class SeriesExploreScreen : BaseFragment<FragmentSeriesExploreBinding, ExploreRouter, MainNavigator>(
    R.layout.fragment_series_explore
) {
    override val navigator: MainNavigator by journeyViewModel()

    override val viewModel by journeyViewModel<SeriesViewModel>()

    private lateinit var airingTodayAdapter: SeriesAdapter
    private lateinit var onTheAirAdapter: SeriesAdapter
    private lateinit var popularAdapter: SeriesAdapter
    private lateinit var topRatedAdapter: SeriesAdapter

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSeriesExploreBinding) {
        setup()
        observer()
    }

    private fun setup() {
        airingTodayAdapter = SeriesAdapter(
            genreList = { item ->
                setGenre(item)
            },
            onItemClick = {

            }
        )

        onTheAirAdapter = SeriesAdapter(
            genreList = { item ->
                setGenre(item)
            },
            onItemClick = {

            }
        )

        popularAdapter = SeriesAdapter(
            genreList = { item ->
                setGenre(item)
            },
            onItemClick = {

            }
        )

        topRatedAdapter = SeriesAdapter(
            genreList = { item ->
                setGenre(item)
            },
            onItemClick = {

            }
        )

        with(binding) {
            airingTodayRV.apply {
                adapter = airingTodayAdapter
                setHasFixedSize(true)
            }

            onTheAirRV.apply {
                adapter = onTheAirAdapter
                setHasFixedSize(true)
            }

            popularRV.apply {
                adapter = popularAdapter
                setHasFixedSize(true)
            }

            topRatedRV.apply {
                adapter = topRatedAdapter
                setHasFixedSize(true)
            }

            airingTodayTV.setOnClickListener {

            }

            onTheAirTV.setOnClickListener {

            }

            popularTV.setOnClickListener {

            }

            topRatedTV.setOnClickListener {

            }
        }
    }

    private fun observer() {
        viewModel.airingTodayList.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {}
                is ResponseStatus.Error -> {}
                is ResponseStatus.Success -> {
                    airingTodayAdapter.submitList(status.data.results?.take(5))
                }
            }
        }

        viewModel.onTheAirList.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {}
                is ResponseStatus.Error -> {}
                is ResponseStatus.Success -> {
                    onTheAirAdapter.submitList(status.data.results?.take(5))

                }
            }
        }

        viewModel.popularList.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {}
                is ResponseStatus.Error -> {}
                is ResponseStatus.Success -> {
                    popularAdapter.submitList(status.data.results?.take(5))

                }
            }
        }

        viewModel.topRatedList.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {}
                is ResponseStatus.Error -> {}
                is ResponseStatus.Success -> {
                    topRatedAdapter.submitList(status.data.results?.take(5))
                }
            }
        }
    }

    private fun setGenre(series: SeriesItem): String {
        var genreList = ""
        val seriesGenres = (viewModel.seriesGenreList.value as? ResponseStatus.Success<Genres>)?.data?.genres
        series.genreIds?.forEach { id ->
            val genre = seriesGenres?.find { it?.id.toString() == id.toString() }
            genreList += "${genre?.name}, "
        }

        return genreList.dropLast(2)
    }
}