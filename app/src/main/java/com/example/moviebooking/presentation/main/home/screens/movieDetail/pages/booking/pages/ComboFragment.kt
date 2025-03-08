package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.pages

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.local.ComboData
import com.example.moviebooking.databinding.FragmentComboBinding
import com.example.moviebooking.domain.viewmodels.BookingViewModel
import com.example.moviebooking.presentation.main.home.adapters.ComboAdapter
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.BookingRouter
import com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.dialogs.PaymentDialog

class ComboFragment : BaseFragment<FragmentComboBinding, BookingRouter, MainNavigator>(
    R.layout.fragment_combo
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val bookingViewModel: BookingViewModel by journeyViewModel()

    private lateinit var comboAdapter: ComboAdapter

    override fun initView(savedInstanceState: Bundle?, binding: FragmentComboBinding) {
        setup()
        onClickListener()
        handleQuantityChange()
    }

    private fun setup() {
        comboAdapter = ComboAdapter()
        comboAdapter.submitList(ComboData.comboList)

        with(binding) {
            comboRV.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = comboAdapter
            }
        }
    }

    private fun handleQuantityChange() {
        comboAdapter.onQuantityChangeListener = { combo, quantity ->

        }
    }

    private fun onClickListener() {
        binding.btnConfirm.safeClick {
            bookingViewModel.setCombo(comboAdapter.getSelectedCombos())
            PaymentDialog().show(
                childFragmentManager,
                PaymentDialog::class.java.name
            )
        }
    }
}