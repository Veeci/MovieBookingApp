package com.example.moviebooking.presentation.main.home.chatbot

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseBottomSheetDialog
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.deepseek.ChatMessage
import com.example.moviebooking.data.remote.entities.deepseek.ChatRequest
import com.example.moviebooking.databinding.ChatbotDialogBinding
import com.example.moviebooking.domain.viewmodels.ChatbotViewModel
import com.example.moviebooking.presentation.main.home.adapters.ChatAdapter

class ChatbotBottomSheet : BaseBottomSheetDialog<ChatbotDialogBinding, MainNavigator>(
    R.layout.chatbot_dialog
) {
    override val navigator: MainNavigator by journeyViewModel()
    private val viewModel: ChatbotViewModel by journeyViewModel()

    private val chatMessages = mutableListOf<ChatMessage>()
    private lateinit var chatAdapter: ChatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupListeners()
        observeMessages()
    }

    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(chatMessages)
        binding.messagesRV.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupListeners() {
        binding.btnSend.setOnClickListener {
            val input = binding.etMessage.text.toString().trim()
            if (input.isNotEmpty()) {
                sendMessage(input)
                binding.etMessage.setText("")
            }
        }
    }

    private fun sendMessage(text: String) {
        val userMessage = ChatMessage(content = text, role = "user", isSent = true)
        chatMessages.add(userMessage)
        chatAdapter.notifyItemInserted(chatMessages.size - 1)
        binding.messagesRV.scrollToPosition(chatMessages.size - 1)

        val request = ChatRequest(messages = listOf(userMessage))
        viewModel.sendMessage(request)
    }

    private fun observeMessages() {
        viewModel.chatMessages.observe(viewLifecycleOwner) { response ->
            if (response is ResponseStatus.Success) {
                response.data.choices?.firstOrNull()?.message?.let { aiMessage ->
                    val message = ChatMessage(
                        content = aiMessage.content ?: "",
                        role = aiMessage.role ?: "assistant",
                        isSent = false
                    )
                    chatMessages.add(message)
                    chatAdapter.notifyItemInserted(chatMessages.size - 1)
                    binding.messagesRV.scrollToPosition(chatMessages.size - 1)
                }
            }
        }
    }
}
