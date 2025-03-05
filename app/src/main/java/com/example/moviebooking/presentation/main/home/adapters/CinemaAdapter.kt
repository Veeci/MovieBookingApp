package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Cinema
import com.example.moviebooking.databinding.CinemaItemBinding

class CinemaAdapter : BaseListAdapter<Cinema, CinemaItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.cinema_item

    override fun bindView(holder: VH, binding: CinemaItemBinding, position: Int) {
        val item = getItem(position)

        with(binding) {
            name.text = item.name
            address.text = item.address
            logo.loadImage(
                source = item.logo,
                defaultImage = R.drawable.img_default_placeholder
            )
        }
    }
}