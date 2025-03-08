package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking

import android.os.Bundle
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentBookingBinding
import com.example.moviebooking.domain.viewmodels.BookingViewModel
import com.example.moviebooking.presentation.main.home.adapters.BookingViewPagerAdapter

class BookingFragment : BaseFragment<FragmentBookingBinding, BookingRouter, MainNavigator>(
    R.layout.fragment_booking
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val bookingViewModel: BookingViewModel by journeyViewModel()

    private lateinit var pagingAdapter: BookingViewPagerAdapter

    override fun initView(savedInstanceState: Bundle?, binding: FragmentBookingBinding) {
        setup()
        observe()
    }

    private fun setup() {
        pagingAdapter = BookingViewPagerAdapter(activity)
        binding.bookingViewPager.apply {
            adapter = pagingAdapter
            offscreenPageLimit = 3
            isUserInputEnabled = false
        }
    }

    private fun observe() {
        bookingViewModel.currentPage.observe(viewLifecycleOwner) { currentPage ->
            binding.bookingViewPager.currentItem = currentPage
        }
    }
}
