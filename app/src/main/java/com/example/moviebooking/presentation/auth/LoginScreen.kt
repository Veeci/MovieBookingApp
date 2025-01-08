package com.example.moviebooking.presentation.auth

import android.os.Bundle
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentAuthBinding

class LoginScreen : BaseFragment<FragmentAuthBinding, LoginRouter, MainNavigator>(
    R.layout.fragment_auth
) {
    override val navigator: MainNavigator by navigatorViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentAuthBinding) {
        TODO("Not yet implemented")
    }
}