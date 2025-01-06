package com.example.moviebooking.presentation.auth.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentSignUpScreenBinding

class SignUpScreen : BaseFragment<FragmentSignUpScreenBinding, SignUpNavigator>(
    R.layout.fragment_sign_up_screen
) {
    override val navigator: SignUpNavigator by navigatorViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSignUpScreenBinding) {
        TODO("Not yet implemented")
    }
}