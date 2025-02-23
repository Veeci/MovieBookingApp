package com.example.moviebooking.presentation.main.home.screens.movieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentMovieDetailScreenBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.home.adapters.MovieDetailPagerAdapter
import com.google.android.material.tabs.TabLayout

class MovieDetailScreen : BaseFragment<FragmentMovieDetailScreenBinding, MovieDetailRouter, MainNavigator>(
    R.layout.fragment_movie_detail_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val viewmodel: MovieViewModel by journeyViewModel()

    private lateinit var adapter: MovieDetailPagerAdapter

    override fun initView(savedInstanceState: Bundle?, binding: FragmentMovieDetailScreenBinding) {
        initialize()
        observe()
        onClickListener()
    }

    private fun initialize() {
        adapter = MovieDetailPagerAdapter(activity)
        with(binding) {
            pageTabs.apply {
                addOnTabSelectedListener(
                    object : TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            when(tab?.position) {
                                0 -> detailViewPager.currentItem = 0
                                1 -> detailViewPager.currentItem = 1
                                2 -> detailViewPager.currentItem = 2
                            }
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {

                        }

                        override fun onTabReselected(tab: TabLayout.Tab?) {

                        }

                    }
                )
            }

            detailViewPager.apply {
                isUserInputEnabled = false
                offscreenPageLimit = 3
                adapter = this@MovieDetailScreen.adapter
            }
        }


    }

    private fun observe() {

    }

    private fun onClickListener() {

    }

}