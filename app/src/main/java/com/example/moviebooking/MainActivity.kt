package com.example.moviebooking

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import com.example.baseproject.presentation.BaseActivity
import com.example.moviebooking.databinding.ActivityMainBinding
import com.example.moviebooking.presentation.widget.WidgetUpdateWorker
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainRouter, MainNavigator>(
    R.layout.activity_main
) {
    override val navigator: MainNavigator by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Base_Theme_MovieBooking)

        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        super.onCreate(savedInstanceState)
    }

    override fun initView(savedInstanceState: Bundle?, binding: ActivityMainBinding) {
        enableNavigationDrawable(false)
        WidgetUpdateWorker.enqueue(applicationContext)
    }
}