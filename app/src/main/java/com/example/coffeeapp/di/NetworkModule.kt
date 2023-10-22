package com.example.coffeeapp.di

import com.example.coffeeapp.data.remote.RestaurantsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRestaurentService(retrofit: Retrofit): RestaurantsService {
        return retrofit.create(RestaurantsService::class.java)
    }
}