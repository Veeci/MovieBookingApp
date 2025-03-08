package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.dialogs

import android.os.Bundle
import com.example.baseproject.domain.utils.LogUtils
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.message
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.positiveAction
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.domain.utils.simpleAlert
import com.example.baseproject.domain.utils.title
import com.example.baseproject.presentation.BaseDialog
import com.example.baseproject.utils.MediaUtil.loadBase64Image
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Payment
import com.example.moviebooking.databinding.PaymentDialogBinding
import com.example.moviebooking.domain.viewmodels.BookingViewModel
import com.example.moviebooking.domain.viewmodels.MovieViewModel

class PaymentDialog : BaseDialog<PaymentDialogBinding, MainNavigator>(
    R.layout.payment_dialog
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val bookingViewModel: BookingViewModel by journeyViewModel()
    private val movieViewModel: MovieViewModel by journeyViewModel()

    override fun initView(savedInstanceState: Bundle?, binding: PaymentDialogBinding) {
        fetchData()
        observe()
        onClickListener()
    }

    private fun fetchData() {
        val movie = movieViewModel.currentMovie.value
        val cinema = bookingViewModel.cinema.value
        val seats = bookingViewModel.seats.value
        val popcorn = bookingViewModel.combo.value

        if (movie == null || cinema == null || seats == null || popcorn == null) {
            LogUtils.log("PaymentDialog", "fetchData: Missing data")
            return
        }

        bookingViewModel.getQRCode(
            Payment(
                movie = movie,
                cinema = cinema,
                seats = seats,
                popcorn = popcorn
            )
        )
    }

    private fun observe() {
        bookingViewModel.qrCode.observe(this) { status ->
            when(status) {
                is ResponseStatus.Loading -> activity.toggleLoading(isLoading = true, preventClicking = true)
                is ResponseStatus.Error -> {
                    activity.toggleLoading(isLoading = false)
                    activity.simpleAlert {
                        title("Payment Error")
                        message("Something went wrong. Please try again later!")
                        positiveAction("OK") {
                            dismiss()
                        }
                    }
                }
                is ResponseStatus.Success -> {
                    activity.toggleLoading(isLoading = false, preventClicking = false)
                    binding.qrCodeIV.loadBase64Image(status.data.data?.qrDataURL)
                }
            }
        }
    }

    private fun onClickListener() {
        binding.complete.safeClick {
            activity.simpleAlert {
                title("Payment Successful")
                message("Your payment has been processed successfully!")
                positiveAction("OK") {
                    dismiss()
                }
            }
        }

        binding.cancel.safeClick { dismiss() }
    }
}