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

//  Hilt module: defines how dependencies are provided
@Module
//  Installed in application scope (lives as long as app)
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    companion object {

        //  Migration from version 1 to 2
        // Handles database schema update safely (no data loss)
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                // 🔹 SQL query to create favourite_meals table
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

        //  Provides Room database instance
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "auth_db"
            )
                // 🔹 Apply migration when DB version updates
                .addMigrations(MIGRATION_1_2)
                .build()

        //  Provides UserDao from database
        @Provides
        @Singleton
        fun provideUserDao(db: AppDatabase): UserDao =
            db.userDao()

        //  Provides FavouriteMealDao from database
        @Provides
        @Singleton
        fun provideFavouriteMealDao(db: AppDatabase): FavouriteMealDao =
            db.favouriteMealDao()
    }
}