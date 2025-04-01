package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.baseproject.utils.MediaUtil.loadImage
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.series.ProductionCompaniesItem
import com.example.moviebooking.databinding.ProductionItemBinding
import com.example.moviebooking.domain.common.Const

class ProductionCompanyAdapter : BaseListAdapter<ProductionCompaniesItem, ProductionItemBinding>() {
    override fun getLayoutId(viewType: Int) = R.layout.production_item

    override fun bindView(holder: VH, binding: ProductionItemBinding, position: Int) {
        val item = getItem(position)

        binding.logoPath.loadImage(
            source = Const.tmdbImageUrlOriginal + item.logoPath,
            defaultImage = R.drawable.img_default_placeholder
        )

        binding.name.text = item.name
    }
}