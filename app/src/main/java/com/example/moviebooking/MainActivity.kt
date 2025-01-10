package com.example.moviebooking

import android.os.Bundle
import com.example.baseproject.presentation.BaseActivity
import com.example.moviebooking.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainRouter, MainNavigator>(
    R.layout.activity_main
) {
    override val navigator: MainNavigator by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Base_Theme_MovieBooking)
        super.onCreate(savedInstanceState)
    }

    override fun initView(savedInstanceState: Bundle?, binding: ActivityMainBinding) {
        enableNavigationDrawable(false)
    }
}