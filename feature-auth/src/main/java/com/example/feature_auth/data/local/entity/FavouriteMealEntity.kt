package com.example.feature_auth.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_meals")
data class FavouriteMealEntity(
    @PrimaryKey val id: String,
    val name: String,
    val thumbnailUrl: String
)