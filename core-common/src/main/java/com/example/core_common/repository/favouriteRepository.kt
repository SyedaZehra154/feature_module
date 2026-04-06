package com.example.core_common.repository

// core-common/src/main/java/com/example/core_common/repository/FavouriteRepository.kt

import com.example.core_common.model.FavouriteMeal
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {
    suspend fun insertFavourite(meal: FavouriteMeal)
    suspend fun deleteFavourite(meal: FavouriteMeal)
    fun getAllFavourites(): Flow<List<FavouriteMeal>>
    fun isFavourite(id: String): Flow<Boolean>
}