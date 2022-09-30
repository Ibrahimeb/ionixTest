package com.example.ionixtest.presentation.features.details.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ionixtest.domain.models.MovieModel
import com.example.ionixtest.presentation.features.details.repository.IDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: IDetailsRepository
) : ViewModel() {

    private val _movieResultLiveData = MutableLiveData<MovieModel>()
    val movieResultLiveData: LiveData<MovieModel> get() = _movieResultLiveData

    fun getMovie(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieResultLiveData.postValue(repository.getMovieById(movieId = id))
        }
    }
}