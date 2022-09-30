package com.example.ionixtest.presentation.features.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

    fun render(model: MovieModel){

    }
}