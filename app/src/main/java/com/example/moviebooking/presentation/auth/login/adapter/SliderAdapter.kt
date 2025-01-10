package com.example.moviebooking.presentation.auth.login.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.smarteist.autoimageslider.SliderViewAdapter
import com.example.moviebooking.R

class SliderAdapter(private val sliderItems: List<SliderItem>) :
    SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): SliderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_slider_layout_item, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
        val sliderItem = sliderItems[position]

        viewHolder.imageView.load(sliderItem.imageUrl)
        viewHolder.textView.text = sliderItem.description
    }

    override fun getCount(): Int {
        return sliderItems.size
    }

    class SliderViewHolder(itemView: View) : ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_slider_image)
        val textView: TextView = itemView.findViewById(R.id.tv_slider_description)
    }
}
