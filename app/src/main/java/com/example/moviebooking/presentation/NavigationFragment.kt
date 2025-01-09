package com.example.moviebooking.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.MainRouter
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentNavigationBinding

class NavigationFragment : BaseFragment<FragmentNavigationBinding, MainRouter, MainNavigator>(
    R.layout.fragment_navigation
) {
    override val navigator: MainNavigator by navigatorViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentNavigationBinding) {

    }
}