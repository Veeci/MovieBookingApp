package com.example.moviebooking.presentation.main.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentExploreScreenBinding
import com.example.moviebooking.presentation.main.home.adapters.ExploreViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class ExploreScreen : BaseFragment<FragmentExploreScreenBinding, ExploreRouter, MainNavigator>(
    R.layout.fragment_explore_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()

    private lateinit var exploreViewPagerAdapter: ExploreViewPagerAdapter

    override fun initView(savedInstanceState: Bundle?, binding: FragmentExploreScreenBinding) {
        setup()
    }

    private fun setup() {
        exploreViewPagerAdapter = ExploreViewPagerAdapter(activity)

        with(binding) {
            pageTabs.addOnTabSelectedListener(
                object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        when (tab?.position) {
                            0 -> tabsViewpager.currentItem = 0
                            1 -> tabsViewpager.currentItem = 1
                            2 -> tabsViewpager.currentItem = 2
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {

                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {

                    }

                }
            )

            tabsViewpager.apply {
                adapter = exploreViewPagerAdapter
                isUserInputEnabled = false
                offscreenPageLimit = 3
            }
        }
    }
}