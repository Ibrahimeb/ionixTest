package com.example.ionixtest.presentation.features.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ionixtest.databinding.FragmentHomeBinding
import com.example.ionixtest.domain.models.MovieModel
import com.example.ionixtest.presentation.features.home.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getMovies()
        viewModel.homeMovieListLiveData.observe(viewLifecycleOwner, ::setupRecyclerView)
    }

    private fun setupRecyclerView(itemList: List<MovieModel>) {
        homeAdapter = HomeAdapter(itemList) {
            // TODO: implement details actions
        }
        binding.recyclerviewHome.apply {
            adapter = homeAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
    }
}