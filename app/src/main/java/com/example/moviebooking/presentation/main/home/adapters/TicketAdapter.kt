package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Ticket
import com.example.moviebooking.databinding.ItemTicketBinding

class TicketAdapter : BaseListAdapter<Ticket, ItemTicketBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.item_ticket

    override fun bindView(holder: VH, binding: ItemTicketBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            cinemaLogoIV.loadImage(
                source = item.detail.cinema.logo,
                defaultImage = R.drawable.img_default_placeholder
            )

            barcodeIV.setImageBitmap(item.barcode)
            movieNameTV.text = item.detail.movie.title
            timeTV.text = item.time
            seatsTV.text = item.detail.getSeats()
            cinemaTV.text = item.detail.cinema.name
        }
    }
}