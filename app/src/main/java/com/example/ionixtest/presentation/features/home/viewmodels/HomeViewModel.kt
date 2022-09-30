package com.example.ionixtest.presentation.features.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ionixtest.data.networkUtils.RequestStatus
import com.example.ionixtest.domain.models.MovieModel
import com.example.ionixtest.presentation.features.home.repositories.IHomeRepository
import com.example.ionixtest.presentation.features.home.usecase.ISorterMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: IHomeRepository,
    private val sorterMoviesUseCase: ISorterMoviesUseCase
) : ViewModel() {

    private val _homeMovieListLiveData = MutableLiveData<List<MovieModel>>()
    val homeMovieListLiveData: LiveData<List<MovieModel>> get() = _homeMovieListLiveData

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.getMovies().collect { requestStatus ->
                when (requestStatus) {
                    is RequestStatus.Error -> throw Exception("error en la consulta")
                    RequestStatus.Loading -> Unit
                    is RequestStatus.Success -> test(requestStatus.value)
                }
            }
        }
    }

    private fun test(value: List<MovieModel>) {
        _homeMovieListLiveData.postValue(sorterMoviesUseCase.invoke(value.toMutableList()))
    }
}