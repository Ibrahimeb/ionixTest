package com.example.ionixtest.presentation.features.home.usecase

import com.example.ionixtest.domain.models.MovieModel

interface ISorterMoviesUseCase {

   fun invoke(listMovie: MutableList<MovieModel>): List<MovieModel>
}