package com.example.ionixtest.data.features.details.repositories

import com.example.ionixtest.data.features.details.dao.MovieDao
import com.example.ionixtest.domain.models.MovieModel
import com.example.ionixtest.presentation.features.details.repository.IDetailsRepository
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val dao: MovieDao
) : IDetailsRepository {
    override suspend fun getMovieById(movieId: String): MovieModel {
        return dao.getSpecificMovieById(movieId).first()
    }
}