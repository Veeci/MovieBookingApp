package com.example.moviebooking.presentation.main.explore.pages.series.seriesDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.MainRouter
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentCelebrityDetailBinding
import com.example.moviebooking.databinding.FragmentSeriesDetailBinding

class SeriesDetailScreen : BaseFragment<FragmentSeriesDetailBinding, SeriesDetailRouter, MainNavigator>(
    R.layout.fragment_series_detail
) {
    override val navigator: MainNavigator by journeyViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSeriesDetailBinding) {

    }

}