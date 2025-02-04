package com.example.moviebooking.presentation.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentProfileScreenBinding

class ProfileScreen : BaseFragment<FragmentProfileScreenBinding, ProfileRouter, MainNavigator>(
    R.layout.fragment_profile_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentProfileScreenBinding) {
    }

}