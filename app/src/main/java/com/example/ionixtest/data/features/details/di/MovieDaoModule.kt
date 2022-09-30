package com.example.ionixtest.data.features.details.di

import com.example.ionixtest.data.database.MovieDataBase
import com.example.ionixtest.data.features.details.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MovieDaoModule {

    @Provides
    fun daoProvider(dataBase: MovieDataBase): MovieDao {
        return dataBase.movieDao()
    }
}