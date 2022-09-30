package com.example.ionixtest.presentation.features.details.repository

import com.example.ionixtest.domain.models.MovieModel

interface IDetailsRepository {

    suspend fun getMovieById(movieId: String): MovieModel
}