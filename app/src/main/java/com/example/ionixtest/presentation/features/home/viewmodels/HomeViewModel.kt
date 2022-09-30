package com.example.ionixtest.presentation.features.home.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ionixtest.data.networkUtils.RequestStatus
import com.example.ionixtest.domain.models.MovieModel
import com.example.ionixtest.presentation.features.home.contract.HomeEvents
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

    private val _eventsHomeLiveData = MutableLiveData<HomeEvents>()
    val eventsHomeLiveData: LiveData<HomeEvents> get() = _eventsHomeLiveData

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            homeRepository.getMovies().collect { requestStatus ->
                when (requestStatus) {
                    is RequestStatus.Error -> errorEventAction()
                    RequestStatus.Loading -> _eventsHomeLiveData.postValue(HomeEvents.IsProgressBar(true))
                    is RequestStatus.Success -> successEventAction(requestStatus.value)
                }
            }
        }
    }

    private fun errorEventAction() {
        _eventsHomeLiveData.postValue(HomeEvents.IsProgressBar(false))
        _eventsHomeLiveData.postValue(HomeEvents.ErrorRequest)
    }

    private fun successEventAction(value: List<MovieModel>) {
        _eventsHomeLiveData.postValue(HomeEvents.IsProgressBar(false))
        _eventsHomeLiveData.postValue(HomeEvents.SuccessRequest(sorterMoviesUseCase.invoke(value.toMutableList())))
    }
}