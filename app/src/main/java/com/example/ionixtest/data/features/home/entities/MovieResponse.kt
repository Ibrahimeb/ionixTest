package com.example.ionixtest.data.features.home.entities

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("errorMessage")
	val errorMessage: String? = null,

	@field:SerializedName("items")
	val items: List<MoviesItem> = emptyList()
)

