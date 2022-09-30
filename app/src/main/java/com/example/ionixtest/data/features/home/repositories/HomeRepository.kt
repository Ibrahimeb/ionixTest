package com.example.ionixtest.data.features.home.repositories

import com.example.ionixtest.data.networkUtils.RequestStatus
import com.example.ionixtest.presentation.features.home.repositories.IHomeRepository
import com.example.ionixtest.data.features.home.api.HomeApi
import com.example.ionixtest.data.features.home.entities.toModel
import com.example.ionixtest.data.networkUtils.HandlerResultHelper
import com.example.ionixtest.domain.models.MovieModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository @Inject constructor(
    private val homeApi: HomeApi
) : IHomeRepository {

    override suspend fun getMovies(): Flow<RequestStatus<List<MovieModel>>> {
        return flow {
            emit(RequestStatus.Loading)
            val result = HandlerResultHelper.getResult {
                homeApi.getListMovie().items.map { it.toModel() }
            }
            emit(result)
        }
    }
}