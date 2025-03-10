package com.example.moviebooking.presentation.main.tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentTicketsScreenBinding
import com.example.moviebooking.domain.viewmodels.BookingViewModel
import com.example.moviebooking.presentation.main.home.adapters.TicketAdapter

class TicketsScreen : BaseFragment<FragmentTicketsScreenBinding, TicketsRouter, MainNavigator>(
    R.layout.fragment_tickets_screen
) {
    override val navigator: MainNavigator by navigatorViewModel()
    private val bookingViewModel by journeyViewModel<BookingViewModel>()

    private lateinit var ticketAdapter: TicketAdapter

    override fun initView(savedInstanceState: Bundle?, binding: FragmentTicketsScreenBinding) {
        observe()
        setup()
    }

    private fun setup() {
        ticketAdapter = TicketAdapter()
        binding.ticketsRV.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ticketAdapter
            setHasFixedSize(true)
        }
    }

    private fun observe() {
        bookingViewModel.ticket.observe(viewLifecycleOwner) { tickets ->
            ticketAdapter.submitList(tickets)
        }
    }
}