package com.example.feature_meals.presentation.di

import com.example.feature_meals.data.remote.MealApi
import com.example.feature_meals.data.repository.MealRepositoryImpl
import com.example.feature_meals.domain.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

// Hilt module: used to provide dependencies
@Module

//  Installed at application level (Singleton scope)
@InstallIn(SingletonComponent::class)
object MealModule {

    //  Provides MealApi implementation using Retrofit
    // Retrofit dynamically creates the implementation of the interface
    @Provides
    @Singleton
    fun provideMealApi(retrofit: Retrofit): MealApi =
        retrofit.create(MealApi::class.java)

    //  Provides MealRepository interface implementation
    // Binds MealRepository to MealRepositoryImpl
    @Provides
    @Singleton
    fun provideMealRepository(impl: MealRepositoryImpl): MealRepository = impl
}