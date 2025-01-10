package com.example.moviebooking.presentation.auth.signup

import android.os.Bundle
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSignUpScreenBinding

class SignUpScreen : BaseFragment<FragmentSignUpScreenBinding, SignUpRouter, MainNavigator>(
    R.layout.fragment_sign_up_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSignUpScreenBinding) {
        TODO("Not yet implemented")
    }
}