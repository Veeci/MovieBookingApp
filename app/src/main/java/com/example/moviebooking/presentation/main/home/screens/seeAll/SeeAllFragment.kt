package com.example.moviebooking.presentation.main.home.screens.seeAll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.baseproject.domain.utils.ResponseStatus
import com.example.baseproject.domain.utils.journeyViewModel
import com.example.baseproject.domain.utils.navigatorViewModel
import com.example.baseproject.domain.utils.safeClick
import com.example.baseproject.presentation.BaseFragment
import com.example.baseproject.presentation.widgets.BaseListAdapter
import com.example.moviebooking.MainNavigator
import com.example.moviebooking.R
import com.example.moviebooking.data.remote.entities.tmdb.movie.Movie
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieItem
import com.example.moviebooking.data.remote.entities.tmdb.movie.MovieList
import com.example.moviebooking.databinding.FragmentSeeAllBinding
import com.example.moviebooking.domain.viewmodels.MovieViewModel
import com.example.moviebooking.presentation.main.home.adapters.MovieItemAdapter
import com.google.android.material.tabs.TabLayout

class SeeAllFragment : BaseFragment<FragmentSeeAllBinding, SeeAllRouter, MainNavigator>(
    R.layout.fragment_see_all
) {
    override val navigator: MainNavigator by navigatorViewModel()

    private val movieViewModel: MovieViewModel by journeyViewModel()

    private lateinit var movieItemAdapter: MovieItemAdapter
    private var nowPlayingList: List<MovieItem?>? = null
    private var popularList: List<MovieItem?>? = null
    private var topRatedList: List<MovieItem?>? = null
    private var upcomingList: List<MovieItem?>? = null

    override fun initView(savedInstanceState: Bundle?, binding: FragmentSeeAllBinding) {
        observe()
        setup()
        onClickListener()
    }

    private fun setup() {
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0))

        movieItemAdapter = MovieItemAdapter().apply {
            action = object : BaseListAdapter.Action<MovieItem> {
                override fun click(position: Int, data: MovieItem, code: Int) {
                    navigator.goToMovieDetailFromSeeAll(
                        Bundle().apply {
                            putString("movieID", data.id?.toString())
                        }
                    )
                }

            }
        }

        binding.movieListRV.apply {
            setHasFixedSize(true)
            setItemViewCacheSize(20)
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@SeeAllFragment.movieItemAdapter
        }

        binding.tabLayout.apply {
            addOnTabSelectedListener(
                object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        when(tab?.position) {
                            0 -> movieItemAdapter.submitList(nowPlayingList ?: emptyList())
                            1 -> movieItemAdapter.submitList(popularList ?: emptyList())
                            2 -> movieItemAdapter.submitList(topRatedList ?: emptyList())
                            4 -> movieItemAdapter.submitList(upcomingList ?: emptyList())
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {

                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                        binding.movieListRV.scrollToPosition(0)
                    }

                }
            )
        }
    }

    private fun onClickListener() {
        with(binding) {
            backBtn.safeClick { router?.onPopScreen() }
        }
    }

    private fun observe() {
        movieViewModel.nowPLayingList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                    //TODO
                }
                is ResponseStatus.Success -> {
                    movieItemAdapter.submitList(status.data.results)
                    nowPlayingList = status.data.results
                    showLoading(isLoading = false)
                }
            }
        }

        movieViewModel.topRatedList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                    //TODO
                }
                is ResponseStatus.Success -> {
                    topRatedList = status.data.results
                    showLoading(isLoading = false)
                }
            }
        }

        movieViewModel.upcomingList.observe(viewLifecycleOwner) { status ->
            when (status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                    //TODO
                }
                is ResponseStatus.Success -> {
                    upcomingList = status.data.results
                    showLoading(isLoading = false)
                }
            }
        }

        movieViewModel.popularList.observe(viewLifecycleOwner) { status ->
            when(status) {
                is ResponseStatus.Loading -> showLoading(isLoading = true)
                is ResponseStatus.Error -> {
                    showLoading(isLoading = false)
                    //TODO
                }
                is ResponseStatus.Success -> {
                    popularList = status.data.results
                    showLoading(isLoading = false)
                }
            }
        }
    }
}