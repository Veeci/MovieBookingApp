package com.example.moviebooking.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviebooking.presentation.main.explore.ExploreScreen
import com.example.moviebooking.presentation.main.home.HomeScreen
import com.example.moviebooking.presentation.main.profile.ProfileScreen
import com.example.moviebooking.presentation.main.tickets.TicketsScreen

class MainVPAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeScreen()
            1 -> ExploreScreen()
            2 -> TicketsScreen()
            else -> ProfileScreen()
        }
    }

}