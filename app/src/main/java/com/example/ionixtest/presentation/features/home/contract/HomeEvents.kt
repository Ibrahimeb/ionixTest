package com.example.ionixtest.presentation.features.home.contract

import com.example.ionixtest.domain.models.MovieModel

sealed class HomeEvents {
    class IsProgressBar(val isLoading: Boolean) : HomeEvents()
    object ErrorRequest : HomeEvents()
    class SuccessRequest(val listItem: List<MovieModel>) : HomeEvents()
}
