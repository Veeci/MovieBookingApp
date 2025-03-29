package com.example.moviebooking.presentation.main.explore.pages.celebrities.celebDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.MainRouter
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentCelebrityDetailBinding

class CelebrityDetailScreen : BaseFragment<FragmentCelebrityDetailBinding, MainRouter, MainNavigator>(
    R.layout.fragment_celebrity_detail
) {
    override val navigator: MainNavigator by journeyViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: FragmentCelebrityDetailBinding) {

    }

}