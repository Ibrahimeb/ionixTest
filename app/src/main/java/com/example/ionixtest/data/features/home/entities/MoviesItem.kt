package com.example.ionixtest.data.features.home.entities

import com.example.ionixtest.domain.models.MovieModel
import com.google.gson.annotations.SerializedName

data class MoviesItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("fullTitle")
    val fullTitle: String? = null,

    @field:SerializedName("runtimeMins")
    val runtimeMins: String? = null,

    @field:SerializedName("year")
    val year: String? = null,

    @field:SerializedName("directors")
    val directors: String? = null,

    @field:SerializedName("genreList")
    val genreList: List<GenreListItem>? = null,

    @field:SerializedName("metacriticRating")
    val metacriticRating: String? = null,

    @field:SerializedName("directorList")
    val directorList: List<DirectorListItem>? = null,

    @field:SerializedName("stars")
    val stars: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("imDbRating")
    val imDbRating: String? = null,

    @field:SerializedName("runtimeStr")
    val runtimeStr: String? = null,

    @field:SerializedName("imDbRatingCount")
    val imDbRatingCount: String? = null,

    @field:SerializedName("plot")
    val plot: String? = null,

    @field:SerializedName("genres")
    val genres: String? = null,

    @field:SerializedName("contentRating")
    val contentRating: String? = null,

    @field:SerializedName("starList")
    val starList: List<StarListItem>? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("releaseState")
    val releaseState: String? = null
)

fun MoviesItem.toModel() =
    MovieModel(
        title = title.orEmpty(),
        image = image.orEmpty(),
        id = id.orEmpty(),
        releaseState = releaseState.orEmpty()
    )