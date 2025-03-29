package com.example.moviebooking.presentation.main.home.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviebooking.presentation.main.explore.pages.celebrities.CelebritiesExploreScreen
import com.example.moviebooking.presentation.main.explore.pages.movies.MoviesExploreScreen
import com.example.moviebooking.presentation.main.explore.pages.series.SeriesExploreScreen

class ExploreViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MoviesExploreScreen()
            1 -> SeriesExploreScreen()
            2 -> CelebritiesExploreScreen()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}