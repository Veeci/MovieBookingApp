package com.example.moviebooking.presentation.main.home.screens.searching.dialog

import android.os.Bundle
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseDialog
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.DialogFilterBinding

class FilterDialog : BaseDialog<DialogFilterBinding, MainNavigator>(
    R.layout.dialog_filter
) {
    override val navigator: MainNavigator by journeyViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: DialogFilterBinding) {

    }
}