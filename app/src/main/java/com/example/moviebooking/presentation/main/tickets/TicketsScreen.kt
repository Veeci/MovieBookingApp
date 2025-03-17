package com.example.moviebooking.presentation.main.tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.domain.utils.LogUtils
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.local.Ticket
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
        setup()
        observe()
    }

    private fun setup() {
        ticketAdapter = TicketAdapter().apply {
            action = object : BaseListAdapter.Action<Ticket> {
                override fun click(position: Int, data: Ticket, code: Int) {
                    TicketInfoDialog(data).show(
                        childFragmentManager,
                        TicketInfoDialog::class.java.name
                    )
                }
            }
        }

        binding.ticketsRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ticketAdapter
        }
    }

    private fun observe() {
        bookingViewModel.ticket.observe(viewLifecycleOwner) { tickets ->
            ticketAdapter.submitList(tickets.toList())
            LogUtils.log("TicketsScreen", "observe: ${tickets.size}")
        }
    }
}