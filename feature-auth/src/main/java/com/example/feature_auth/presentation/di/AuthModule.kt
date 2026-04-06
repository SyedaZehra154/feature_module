package com.example.feature_auth.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.core_common.repository.FavouriteRepository
import com.example.feature_auth.data.local.dao.FavouriteMealDao
import com.example.feature_auth.data.local.dao.UserDao
import com.example.feature_auth.data.local.database.AppDatabase
import com.example.feature_auth.data.repository.AuthRepositoryImpl
import com.example.feature_auth.data.repository.FavouriteRepositoryImpl
import com.example.feature_auth.domain.repo.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    // ✅ newly added — binds FavouriteRepository interface to its implementation
    @Binds
    @Singleton
    abstract fun bindFavouriteRepository(
        impl: FavouriteRepositoryImpl
    ): FavouriteRepository

    companion object {

        // ✅ migration from version 1 to 2
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS favourite_meals (
                        id TEXT NOT NULL PRIMARY KEY,
                        name TEXT NOT NULL,
                        thumbnailUrl TEXT NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "auth_db"
            )
                .addMigrations(MIGRATION_1_2)   // ✅ added migration
                .build()

        @Provides
        @Singleton
        fun provideUserDao(db: AppDatabase): UserDao =
            db.userDao()

        //  newly added
        @Provides
        @Singleton
        fun provideFavouriteMealDao(db: AppDatabase): FavouriteMealDao =
            db.favouriteMealDao()
    }
}