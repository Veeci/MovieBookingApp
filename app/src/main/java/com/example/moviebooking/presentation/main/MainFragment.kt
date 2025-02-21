package com.example.moviebooking.presentation.main

import android.os.Bundle
import android.view.DragEvent
import android.view.View
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

    private var dX: Float = 0.0f
    private var dY: Float = 0.0f

    override fun initView(savedInstanceState: Bundle?, binding: FragmentMainBinding) {
        setupPages()
        setupBottomBar()
        setupMovableFab()
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

    private fun setupMovableFab() {
        binding.root.setOnDragListener { v, event ->
            when(event.action) {
                DragEvent.ACTION_DRAG_LOCATION -> {
                    dX = event.x
                    dY = event.y
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    binding.chatbotBtn.x = dX - binding.chatbotBtn.width / 2
                    binding.chatbotBtn.y = dY - binding.chatbotBtn.height / 2
                }
            }

            true
        }

        binding.chatbotBtn.setOnLongClickListener { v ->
            val shadow = View.DragShadowBuilder(binding.chatbotBtn)
            v.startDragAndDrop(null, shadow, null, View.DRAG_FLAG_GLOBAL)
            true
        }
    }
}