package com.example.moviebooking

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.baseproject.presentation.BaseActivity
import com.example.baseproject.presentation.navigation.PermissionResultEvent
import com.example.moviebooking.databinding.ActivityMainBinding

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