package com.example.moviebooking

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.observer
import com.example.baseproject.domain.utils.toastShort
import com.example.baseproject.presentation.BaseActivity
import com.example.baseproject.presentation.navigation.PermissionResultEvent
import com.example.moviebooking.databinding.ActivityMainBinding
import com.example.moviebooking.domain.usecases.movies.MovieViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainNavigator>(
    R.layout.activity_main
) {
    override val navigator: MainNavigator by viewModels()

    override fun initView(savedInstanceState: Bundle?, binding: ActivityMainBinding) {
        binding.root.postDelayed({
            navigator.sendEvent(PermissionResultEvent(0, arrayOf(""), intArrayOf(), 0))
        }, 3000)
    }
}