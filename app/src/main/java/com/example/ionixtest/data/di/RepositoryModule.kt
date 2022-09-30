package com.example.ionixtest.data.di

import com.example.ionixtest.data.features.home.repositories.HomeRepository
import com.example.ionixtest.presentation.features.home.repositories.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsHomeRepository(repository: HomeRepository): IHomeRepository
}