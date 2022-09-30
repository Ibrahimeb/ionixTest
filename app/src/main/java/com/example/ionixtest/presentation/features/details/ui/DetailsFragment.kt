package com.example.ionixtest.presentation.features.details.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.ionixtest.commons.glideConfig
import com.example.ionixtest.databinding.FragmentDetailsBinding
import com.example.ionixtest.presentation.features.details.viewmodels.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val viewModel: DetailsViewModel by viewModels()

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovie(args.movieId)
        viewModel.movieResultLiveData.observe(viewLifecycleOwner) { movie ->
            binding.apply {
                glideConfig(movie.image, imageviewDetailPoster)
                textviewDetailsTitle.text = movie.title
                textviewDetailsReleaseDate.text = "release date:\n${movie.releaseState}"
                textviewDetailsPlot.text = movie.plot
                var strStars = "actors:\n"
                movie.stars.forEach {
                    strStars += "$it \n"
                }
                textviewDetailsStars.text = strStars
            }
        }
    }
}