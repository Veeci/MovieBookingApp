package com.example.moviebooking.presentation.main.home.adapters

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Date
import com.example.moviebooking.databinding.DateItemBinding

class DateAdapter(
    private val onItemClicked: (Date) -> Unit
) : BaseListAdapter<Date, DateItemBinding>() {
    private var selectedPosition = -1

    override fun getLayoutId(viewType: Int) = R.layout.date_item

    @RequiresApi(Build.VERSION_CODES.O)
    override fun bindView(holder: VH, binding: DateItemBinding, position: Int) {
        val item = getItem(position)
        binding.itemDate.text = item.displayFormat()
        binding.itemDate.isSelected = (position == selectedPosition)
        binding.root.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = position

            if(previousPosition != -1) notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)

            onItemClicked(item)
        }
    }
}