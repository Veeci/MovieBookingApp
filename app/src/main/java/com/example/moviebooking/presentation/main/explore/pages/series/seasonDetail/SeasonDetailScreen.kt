package com.example.moviebooking.presentation.main.explore.pages.series.seasonDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSeasonDetailScreenBinding
import com.example.moviebooking.presentation.main.MainRouter

class SeasonDetailScreen : BaseFragment<FragmentSeasonDetailScreenBinding, MainRouter, MainNavigator>(
    R.layout.fragment_season_detail_screen
) {
    override val navigator: MainNavigator by journeyViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSeasonDetailScreenBinding) {

    }
}