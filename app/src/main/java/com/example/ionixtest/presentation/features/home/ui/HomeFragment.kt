package com.example.ionixtest.presentation.features.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ionixtest.databinding.FragmentHomeBinding
import com.example.ionixtest.domain.models.MovieModel
import com.example.ionixtest.presentation.features.home.contract.HomeEvents
import com.example.ionixtest.presentation.features.home.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.eventsHomeLiveData.observe(viewLifecycleOwner, ::handlerEvent)
        binding.outlinedButton.setOnClickListener {
            viewModel.getMovies()
        }
    }

    private fun handlerEvent(events: HomeEvents) {
        when (events) {
            HomeEvents.ErrorRequest -> binding.apply {
                errorGroup.isVisible = true
                progressbarHome.isVisible = false
            }
            is HomeEvents.IsProgressBar -> binding.progressbarHome.isVisible = events.isLoading
            is HomeEvents.SuccessRequest -> {
                binding.errorGroup.isVisible = false
                setupRecyclerView(events.listItem)
            }
        }
    }

    private fun setupRecyclerView(itemList: List<MovieModel>) {
        homeAdapter = HomeAdapter(itemList) {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it))
        }
        binding.recyclerviewHome.apply {
            adapter = homeAdapter
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }
    }
}