package com.example.ionixtest.presentation.features.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ionixtest.R
import com.example.ionixtest.commons.glideConfig
import com.example.ionixtest.databinding.ViewMovieItemBinding
import com.example.ionixtest.domain.models.MovieModel

class HomeViewHolder(private val binding: ViewMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(viewGroup: ViewGroup): HomeViewHolder {
            val binding = ViewMovieItemBinding
                .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return HomeViewHolder(binding)
        }
    }

    fun render(model: MovieModel, callback: (movieId: String) -> Unit) {
        binding.apply {
            textviewTitle.text = model.title
            textviewDate.text = model.releaseState
            glideConfig(model.image, imageviewPoster)
            root.setOnClickListener {
                callback(model.id)
            }
        }
    }
}