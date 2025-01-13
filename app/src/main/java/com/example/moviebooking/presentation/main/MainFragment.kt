package com.example.moviebooking.presentation.main

import android.os.Bundle
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding, MainRouter, MainNavigator>(
    R.layout.fragment_main
) {
    override val navigator: MainNavigator by navigatorViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentMainBinding) {

    }

}