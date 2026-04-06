package com.example.feature_auth.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.feature_auth.data.local.dao.FavouriteMealDao
import com.example.feature_auth.data.local.dao.UserDao
import com.example.feature_auth.data.local.entity.FavouriteMealEntity
import com.example.feature_auth.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,           //  existing
        FavouriteMealEntity::class   //  newly added
    ],
    version = 2,                     //  bumped from 1 to 2
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao              //  existing
    abstract fun favouriteMealDao(): FavouriteMealDao  //  newly added
}