package com.example.ionixtest.data.di

import android.content.Context
import androidx.room.Room
import com.example.ionixtest.data.database.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    private companion object {
        private const val DATABASE_NAME = "app.store.database"
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MovieDataBase {
        return Room.databaseBuilder(
            appContext,
            MovieDataBase::class.java,
            DATABASE_NAME
        ).build()
    }
}