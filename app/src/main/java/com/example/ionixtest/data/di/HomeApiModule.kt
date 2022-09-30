package com.example.ionixtest.data.di

import com.example.ionixtest.data.features.home.api.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class HomeApiModule {

    @Provides
    fun homeApiProvider(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }
}