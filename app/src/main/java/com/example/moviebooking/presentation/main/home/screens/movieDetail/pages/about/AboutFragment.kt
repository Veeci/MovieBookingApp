package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.about

import android.os.Bundle
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentAboutBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel

class AboutFragment : BaseFragment<FragmentAboutBinding, AboutRouter, MainNavigator>(
    R.layout.fragment_about
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val movieViewmodel: MovieViewModel by journeyViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentAboutBinding) {
        setup()
        fetchData()
        observe()
    }

    private fun setup() {
        with(binding) {
            overview.apply {
                setShowingLine(4)
                addShowMoreText("Show more")
                addShowLessText("Show less")
                setShowMoreTextColor(R.color.ThemePrimary)
                setShowLessTextColor(R.color.ThemeTertiary)
            }
        }
    }

    private fun fetchData() {

    }

    private fun observe() {

    }
}