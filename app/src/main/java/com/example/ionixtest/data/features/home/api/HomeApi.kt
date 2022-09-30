package com.example.ionixtest.data.features.home.api

import com.example.ionixtest.data.features.home.entities.MovieResponse
import retrofit2.http.GET

interface HomeApi {

    @GET(URL_HOME)
    suspend fun getListMovie(): MovieResponse

    private companion object {
        const val URL_HOME = "movies.json?key=cb03b960"
    }
}