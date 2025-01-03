package com.example.moviebooking.presentation.auth.splash

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.presentation.navigation.BaseNavigator
import com.example.moviebooking.databinding.FragmentSplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : BaseFragment<FragmentSplashScreenBinding, BaseNavigator>() {
    override val navigator: SplashNavigator by navigatorViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSplashScreenBinding) {
        TODO("Not yet implemented")
    }

}