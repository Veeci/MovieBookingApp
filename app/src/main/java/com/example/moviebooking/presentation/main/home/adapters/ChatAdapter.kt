package com.example.moviebooking.presentation.main.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.domain.utils.gone
import com.example.baseproject.domain.utils.visible
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.deepseek.ChatMessage

class ChatAdapter(private val messages: List<ChatMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val VIEW_TYPE_SENT = 1
        private const val VIEW_TYPE_RECEIVED = 2
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isSent) VIEW_TYPE_SENT else VIEW_TYPE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_SENT) {
            val view = inflater.inflate(R.layout.item_message_sent, parent, false)
            SentViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_message_received, parent, false)
            ReceivedViewHolder(view)
        }
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position !in messages.indices) return

        val message = messages[position]
        if (holder is SentViewHolder) holder.bind(message.content)
        else if (holder is ReceivedViewHolder) {
            if (message.isTyping) {
                holder.showTyping()
            } else {
                holder.bind(message.content)
            }
        }
    }

    inner class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {
            itemView.findViewById<AppCompatTextView>(R.id.messageSentTV).text = text
        }
    }

    inner class ReceivedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {
            itemView.findViewById<AppCompatTextView>(R.id.messageReceivedTV).text = text
        }

        fun showTyping() {
            itemView.findViewById<AppCompatTextView>(R.id.messageReceivedTV).gone()
            itemView.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.typingAnimation).visible()
        }
    }
}
