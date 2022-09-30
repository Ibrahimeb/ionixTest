package com.example.ionixtest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ionixtest.data.features.details.dao.MovieDao
import com.example.ionixtest.domain.models.Converters
import com.example.ionixtest.domain.models.MovieModel

@Database(entities = [MovieModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}