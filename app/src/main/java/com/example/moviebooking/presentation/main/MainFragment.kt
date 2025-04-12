package com.example.moviebooking.presentation.main

import android.os.Bundle
import android.view.DragEvent
import android.view.View
import com.example.baseproject.domain.utils.message
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.negativeAction
import com.example.baseproject.domain.utils.positiveAction
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.domain.utils.simpleAlert
import com.example.baseproject.domain.utils.title
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentMainBinding
import com.example.moviebooking.presentation.main.home.chatbot.ChatbotBottomSheet

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

    override fun onBackPressed(): Boolean {
        activity.simpleAlert {
            title("Exit app")
            message("Are you sure you want to exit?")
            positiveAction("Yes") {
                activity.finish()
            }
            negativeAction("No") {
                dismiss()
            }
        }

        return true
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
        with(binding) {
            root.setOnDragListener { v, event ->
                when(event.action) {
                    DragEvent.ACTION_DRAG_LOCATION -> {
                        dX = event.x
                        dY = event.y
                    }

                    DragEvent.ACTION_DRAG_ENDED -> {
                        chatbotBtn.x = dX - chatbotBtn.width / 2
                        chatbotBtn.y = dY - chatbotBtn.height / 2
                    }
                }

                true
            }

            chatbotBtn.setOnLongClickListener { v ->
                val shadow = View.DragShadowBuilder(chatbotBtn)
                v.startDragAndDrop(null, shadow, null, View.DRAG_FLAG_GLOBAL)
                true
            }

            chatbotBtn.safeClick {
                ChatbotBottomSheet().show(parentFragmentManager, ChatbotBottomSheet::class.java.name)
            }
        }


    }
}