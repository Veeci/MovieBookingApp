package com.example.moviebooking.presentation.main.home.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.about.AboutFragment
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.BookingFragment
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.similar.SimilarFragment

class MovieDetailPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> AboutFragment()
            1 -> BookingFragment()
            else -> SimilarFragment()
        }
    }
}