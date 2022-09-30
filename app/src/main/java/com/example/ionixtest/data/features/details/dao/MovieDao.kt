package com.example.ionixtest.data.features.details.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ionixtest.domain.models.MovieModel

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovie(movie: MovieModel)

    @Query("SELECT * FROM movie WHERE id = :movieId")
    suspend fun getSpecificMovieById(movieId: String): List<MovieModel>

    @Query("SELECT * FROM movie")
    suspend fun getAllMovie(): List<MovieModel>
}