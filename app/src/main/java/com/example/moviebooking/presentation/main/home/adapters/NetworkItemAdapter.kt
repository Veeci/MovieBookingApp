package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.series.NetworksItem
import com.example.moviebooking.databinding.NetworkItemBinding
import com.example.moviebooking.domain.common.Const

class NetworkItemAdapter : BaseListAdapter<NetworksItem, NetworkItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.network_item

    override fun bindView(holder: VH, binding: NetworkItemBinding, position: Int) {
        val item = getItem(position)

        binding.nameTV.text = item.name
        binding.logoPathIV.loadImage(
            source = Const.tmdbImageUrlW500 + item.logoPath,
            defaultImage = R.drawable.img_default_placeholder
        )

    }
}