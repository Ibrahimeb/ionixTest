package com.example.ionixtest.data.features.home.entities

import com.google.gson.annotations.SerializedName

data class DirectorListItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)