package com.example.moviebooking.presentation.main.home.adapters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.pages.CinemaFragment
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.pages.ComboFragment
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.pages.SeatViewFragment

class BookingViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> CinemaFragment()
            1 -> SeatViewFragment()
            2 -> ComboFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}