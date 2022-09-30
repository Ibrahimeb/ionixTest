package com.example.ionixtest.data.features.details.di

import com.example.ionixtest.data.features.details.repositories.DetailsRepository
import com.example.ionixtest.presentation.features.details.repository.IDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DetailsRepositoryModule {

    @Binds
    abstract fun bindsRepository(repository: DetailsRepository): IDetailsRepository
}