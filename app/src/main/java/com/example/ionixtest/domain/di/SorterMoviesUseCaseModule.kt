package com.example.ionixtest.domain.di

import com.example.ionixtest.domain.usecase.SorterMoviesUseCase
import com.example.ionixtest.presentation.features.home.usecase.ISorterMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SorterMoviesUseCaseModule {

    @Binds
    abstract fun bindSorterMovies(useCase: SorterMoviesUseCase): ISorterMoviesUseCase
}