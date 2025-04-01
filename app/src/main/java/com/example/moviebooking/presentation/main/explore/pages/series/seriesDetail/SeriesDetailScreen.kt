package com.example.moviebooking.presentation.main.explore.pages.series.seriesDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.MainRouter
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.series.Series
import com.example.moviebooking.data.remote.entities.tmdb.series.SeriesItem
import com.example.moviebooking.databinding.FragmentCelebrityDetailBinding
import com.example.moviebooking.databinding.FragmentSeriesDetailBinding
import com.example.moviebooking.domain.viewmodels.SeriesViewModel

class SeriesDetailScreen :
    BaseFragment<FragmentSeriesDetailBinding, SeriesDetailRouter, MainNavigator>(
        R.layout.fragment_series_detail
    ) {
    override val navigator: MainNavigator by journeyViewModel()
    override val viewModel by journeyViewModel<SeriesViewModel>()

    private var seriesID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        seriesID = arguments?.getString("seriesID") ?: ""
    }

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSeriesDetailBinding) {
        fetchData()
        observer()
    }

    private fun fetchData() {
        viewModel.fetchSeriesDetail(seriesID)
    }

    private fun observer() {
        viewModel.seriesDetail.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> {}
                is ResponseStatus.Error -> {}
                is ResponseStatus.Success -> {
                    displaySeriesInfo(status.data)
                }
            }
        }
    }

    private fun displaySeriesInfo(series: Series) {

    }
}