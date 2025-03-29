package com.example.moviebooking.presentation.main.explore.pages.celebrities

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseproject.domain.utils.EndlessOnScrollListener
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.presentation.BaseFragment
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.databinding.FragmentCelebritiesExploreScreenBinding
import com.example.moviebooking.domain.viewmodels.PeopleViewModel
import com.example.moviebooking.presentation.main.explore.ExploreRouter
import com.example.moviebooking.presentation.main.home.adapters.CelebrityAdapter

class CelebritiesExploreScreen : BaseFragment<FragmentCelebritiesExploreScreenBinding, ExploreRouter, MainNavigator>(
    R.layout.fragment_celebrities_explore_screen
) {
    override val navigator: MainNavigator by journeyViewModel()

    override val viewModel by journeyViewModel<PeopleViewModel>()

    private lateinit var celebritiesAdapter: CelebrityAdapter

    override fun initView(
        savedInstanceState: Bundle?,
        binding: FragmentCelebritiesExploreScreenBinding
    ) {
        setup()
        observer()
    }

    private fun observer() {
        viewModel.popularPeopleList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> {
                    showLoading(isLoading = true)
                }
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                }
                is ResponseStatus.Success -> {
                    showLoading(false)
                    celebritiesAdapter.submitList(status.data.results ?: emptyList())
                }
            }
        }
    }

    private fun setup() {
        celebritiesAdapter = CelebrityAdapter(
            onItemClicked = {

            }
        )

        binding.celebritiesRecyclerView.apply {
            adapter = celebritiesAdapter
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            addOnScrollListener(object : EndlessOnScrollListener(layoutManager as GridLayoutManager) {
                override fun loadMoreItems(page: Int, totalItemsCount: Int) {
                    viewModel.fetchPeopleList(page)
                }

            })
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }
}