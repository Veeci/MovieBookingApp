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

    private lateinit var adapter: MainVPAdapter

    override fun initView(savedInstanceState: Bundle?, binding: FragmentMainBinding) {
        setupPages()
        setupBottomBar()
    }

    private fun setupPages() {
        adapter = MainVPAdapter(activity)
        binding.mainViewPager.adapter = adapter
        binding.mainViewPager.isUserInputEnabled = false
        binding.mainViewPager.offscreenPageLimit = 4
    }

    private fun setupBottomBar() {
        binding.bottomNavigationBar.setOnItemSelectedListener { tab ->
            when (tab.itemId) {
                R.id.menu_item_home -> {
                    binding.mainViewPager.currentItem = 0
                    true
                }

                R.id.menu_item_explore -> {
                    binding.mainViewPager.currentItem = 1
                    true
                }

                R.id.menu_item_ticket -> {
                    binding.mainViewPager.currentItem = 2
                    true
                }

                R.id.menu_item_profile -> {
                    binding.mainViewPager.currentItem = 3
                    true
                }

                else -> false
            }
        }
    }
}