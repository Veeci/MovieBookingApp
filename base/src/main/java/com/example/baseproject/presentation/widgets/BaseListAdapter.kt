package com.example.baseproject.presentation.widgets

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.baseproject.domain.utils.LogUtils

@Suppress("UNCHECKED_CAST")
abstract class BaseListAdapter<DATA : Any, VIEW_BINDING : ViewBinding>(
    private val diffUtil: DiffUtil.ItemCallback<DATA> = DiffUtilCallBack(),
) : ListAdapter<DATA, BaseListAdapter.VH>(diffUtil) {
    var action: Action<DATA>? = null
    var observe: Observe<DATA>? = null

    abstract fun getLayoutId(viewType: Int): Int

    class VH(val binding: ViewBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): VH {
        return VH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getLayoutId(viewType),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(
        holder: VH,
        position: Int,
    ) {
        try {
            holder.binding.root.setOnClickListener {
                action?.click(holder.bindingAdapterPosition, getItem(position))
            }
            bindView(holder, holder.binding as VIEW_BINDING, position)
        } catch (e: Exception) {
            LogUtils.log("BaseListAdapter", e.message.toString())
        }
    }

    abstract fun bindView(
        holder: VH,
        binding: VIEW_BINDING,
        position: Int,
    )

    interface Action<T> {
        fun click(
            position: Int,
            data: T,
            code: Int = 0,
        )
    }

    interface Observe<T> {
        fun subData(
            root: View,
            childView: View,
            data: T,
            code: Int = 0,
        )
    }
}

class DiffUtilCallBack<DATA : Any> : DiffUtil.ItemCallback<DATA>() {
    override fun areItemsTheSame(
        oldItem: DATA,
        newItem: DATA,
    ) = (oldItem == newItem)

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: DATA,
        newItem: DATA,
    ) = (oldItem == newItem)
}
