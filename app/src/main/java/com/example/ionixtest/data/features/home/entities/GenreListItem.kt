package com.example.ionixtest.data.features.home.entities

import com.google.gson.annotations.SerializedName

data class GenreListItem(

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("key")
	val key: String? = null
)