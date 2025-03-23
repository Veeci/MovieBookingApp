package com.example.moviebooking.presentation.main.home.adapters

import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Time
import com.example.moviebooking.databinding.DateItemBinding

class TimeAdapter(
    private val onItemClicked: (Time) -> Unit
) : BaseListAdapter<Time, DateItemBinding>() {
    private var selectedPosition = -1

    override fun getLayoutId(viewType: Int) = R.layout.date_item

    override fun bindView(holder: VH, binding: DateItemBinding, position: Int) {
        val item = getItem(position)
        binding.itemDate.text = item.time
        binding.itemDate.isSelected = (position == selectedPosition)
        binding.root.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = position
            if(previousPosition != -1) notifyItemChanged(previousPosition)
            notifyItemChanged(position)

            onItemClicked(item)
        }
    }
}