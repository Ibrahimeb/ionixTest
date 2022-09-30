package com.example.ionixtest.data.features.home.repositories

import com.example.ionixtest.data.features.details.dao.MovieDao
import com.example.ionixtest.data.features.home.api.HomeApi
import com.example.ionixtest.data.features.home.entities.toModel
import com.example.ionixtest.data.networkUtils.HandlerResultHelper
import com.example.ionixtest.data.networkUtils.RequestStatus
import com.example.ionixtest.domain.models.MovieModel
import com.example.ionixtest.presentation.features.home.repositories.IHomeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository @Inject constructor(
    private val homeApi: HomeApi,
    private val movieDao: MovieDao
) : IHomeRepository {

    override suspend fun getMovies(): Flow<RequestStatus<List<MovieModel>>> {
        return flow {
            emit(RequestStatus.Loading)
            val result = HandlerResultHelper.getResult {
                homeApi.getListMovie().items.map { it.toModel() }
            }
            if (result is RequestStatus.Success) handlerLocalStorage(result.value)
            emit(result)
        }
    }

    private suspend fun handlerLocalStorage(itemList: List<MovieModel>) {
        if (movieDao.getAllMovie().isEmpty()) {
            itemList.forEach { movie ->
                movieDao.insertMovie(movie)
            }
        }
    }
}