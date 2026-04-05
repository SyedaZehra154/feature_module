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

// presentation/di/MealModule.kt
@Module
@InstallIn(SingletonComponent::class)
object MealModule {

    @Provides
    @Singleton
    fun provideMealApi(retrofit: Retrofit): MealApi =
        retrofit.create(MealApi::class.java)

    @Provides @Singleton
    fun provideMealRepository(impl: MealRepositoryImpl): MealRepository = impl
}