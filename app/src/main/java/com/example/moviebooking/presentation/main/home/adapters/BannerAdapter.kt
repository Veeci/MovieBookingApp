package com.example.moviebooking.presentation.main.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import coil.load
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Banner
import com.smarteist.autoimageslider.SliderViewAdapter

class BannerAdapter(private val bannerItems: List<Banner>) :
    SliderViewAdapter<BannerAdapter.SliderViewHolder>() {
    override fun getCount() = bannerItems.size

    override fun onCreateViewHolder(parent: ViewGroup): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.banner_item, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
        val bannerItem = bannerItems[position]

        viewHolder.imageView.load(bannerItem.image)
    }

    class SliderViewHolder(itemView: View) : ViewHolder(itemView) {
        val imageView: AppCompatImageView = itemView.findViewById(R.id.bannerUrl)

    }
}