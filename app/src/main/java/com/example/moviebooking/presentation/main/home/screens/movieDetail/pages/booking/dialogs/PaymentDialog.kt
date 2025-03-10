package com.example.moviebooking.presentation.main.home.screens.movieDetail.pages.booking.dialogs

import android.graphics.Bitmap
import android.graphics.Color
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
import com.example.moviebooking.data.local.Ticket
import com.example.moviebooking.databinding.PaymentDialogBinding
import com.example.moviebooking.domain.viewmodels.BookingViewModel
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

class PaymentDialog : BaseDialog<PaymentDialogBinding, MainNavigator>(
    R.layout.payment_dialog
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val bookingViewModel: BookingViewModel by journeyViewModel()
    private val movieViewModel: MovieViewModel by journeyViewModel()

    private lateinit var payment: Payment

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

        payment = Payment(
            movie = movie,
            cinema = cinema,
            seats = seats,
            popcorn = popcorn
        )

        bookingViewModel.getQRCode(payment)
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
            bookingViewModel.createTicket(generateTicket())

            activity.simpleAlert {
                title("Payment Successful")
                message("Your payment has been processed successfully!")
                positiveAction("OK") {
                    navigator.toHomeScreen()
                    dismiss()
                }
            }
        }

        binding.cancel.safeClick { dismiss() }
    }

    private fun generateTicket() : Ticket {
        return Ticket(
            detail = payment,
            time = System.currentTimeMillis().toString(),
            barcode = generateQRCode(payment.paymentInfo(), 300, 300)
        )
    }

//    private fun generateBarcode(content: String) : Bitmap {
//        val mwriter = MultiFormatWriter()
//        val bitMatrix = mwriter.encode(content, com.google.zxing.BarcodeFormat.CODE_128, 500, 150)
//        val bitmap = Bitmap.createBitmap(500, 150, Bitmap.Config.ARGB_8888)
//        for (x in 0 until 500) {
//            for (y in 0 until 150) {
//                bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
//            }
//        }
//        return bitmap
//    }

    private fun generateQRCode(content: String, width: Int, height: Int): Bitmap {
        try {
            val hints = HashMap<EncodeHintType, Any>()
            hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
            hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H

            val bitMatrix: BitMatrix = MultiFormatWriter().encode(
                content,
                BarcodeFormat.QR_CODE,
                width,
                height,
                hints
            )

            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            return Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)
        }
    }

}