package com.example.moviebooking.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding, MainNavigator>(
    R.layout.fragment_main
) {
    override val navigator: MainNavigator by navigatorViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentMainBinding) {
        TODO("Not yet implemented")
    }

}