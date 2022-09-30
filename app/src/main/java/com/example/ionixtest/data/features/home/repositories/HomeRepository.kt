package com.example.ionixtest.data.features.home.repositories

import android.util.Log
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
            val remoteResult = launchRemoteRequest()
            if (remoteResult is RequestStatus.Success) {
                emit(remoteResult)
            } else {
                emit(launchLocalStorageFallBack())
            }

        }
    }

    private suspend fun launchRemoteRequest(): RequestStatus<List<MovieModel>> {
        val result = HandlerResultHelper.getResult {
            homeApi.getListMovie().items.map { it.toModel() }
        }
        if (result is RequestStatus.Success) handlerLocalStorage(result.value)
        Log.i(this::class.java.simpleName, "launchRemoteRequest: result : ${result::class.java.simpleName}")
        return result
    }

    private suspend fun handlerLocalStorage(itemList: List<MovieModel>) {
        if (movieDao.getAllMovie().isEmpty()) {
            itemList.forEach { movie ->
                movieDao.insertMovie(movie)
            }
        }
    }

    private suspend fun launchLocalStorageFallBack(): RequestStatus<List<MovieModel>> {
        Log.i(this::class.java.simpleName, "launchLocalStorageFallBack: ")
        return if (movieDao.getAllMovie().isEmpty()) {
            RequestStatus.Error(null, "unknown error")
        } else {
            RequestStatus.Success(movieDao.getAllMovie())
        }
    }
}