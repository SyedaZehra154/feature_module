package com.example.feature_auth.di

import android.content.Context
import androidx.room.Room
import com.example.feature_auth.data.local.dao.UserDao
import com.example.feature_auth.data.local.database.AppDatabase
import com.example.feature_auth.data.repository.AuthRepositoryImpl
import com.example.feature_auth.domain.repo.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "auth_db").build()

        @Provides
        fun provideUserDao(db: AppDatabase): UserDao = db.userDao()
    }
}