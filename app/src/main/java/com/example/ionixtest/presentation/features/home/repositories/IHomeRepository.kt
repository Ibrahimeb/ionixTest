package com.example.ionixtest.presentation.features.home.repositories

import com.example.ionixtest.data.networkUtils.RequestStatus
import com.example.ionixtest.domain.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    suspend fun getMovies(): Flow<RequestStatus<List<MovieModel>>>
}