package com.example.moviebooking.presentation.main.tickets

import android.os.Bundle
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseDialog
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Ticket
import com.example.moviebooking.databinding.TicketDetailDialogBinding
import com.example.moviebooking.domain.common.Const

class TicketInfoDialog(private val ticket: Ticket) : BaseDialog<TicketDetailDialogBinding, MainNavigator>(
    R.layout.ticket_detail_dialog
) {
    override val navigator: MainNavigator by navigatorViewModel()

    override var dismissOnTouchOutside = true

    override fun initView(savedInstanceState: Bundle?, binding: TicketDetailDialogBinding) {
        display()
    }

    private fun display() {
        binding.apply {
            movieIV.loadImage(
                source = Const.tmdbImageUrlW500 + ticket.detail.movie.posterPath,
                defaultImage = R.drawable.img_default_placeholder
            )

            movieInfoTV.text = ticket.detail.paymentInfo()
            barcodeIV.setImageBitmap(ticket.barcode)
        }
    }
}