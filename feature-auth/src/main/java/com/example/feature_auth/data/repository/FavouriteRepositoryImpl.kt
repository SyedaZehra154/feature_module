package com.example.feature_auth.data.repository


import com.example.core_common.model.FavouriteMeal
import com.example.core_common.repository.FavouriteRepository
import com.example.feature_auth.data.local.dao.FavouriteMealDao
import com.example.feature_auth.data.mapper.toDomain
import com.example.feature_auth.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val dao: FavouriteMealDao
) : FavouriteRepository {

    override suspend fun insertFavourite(meal: FavouriteMeal) =
        dao.insertFavourite(meal.toEntity())

    override suspend fun deleteFavourite(meal: FavouriteMeal) =
        dao.deleteFavourite(meal.toEntity())

    override fun getAllFavourites(): Flow<List<FavouriteMeal>> =
        dao.getAllFavourites().map { list -> list.map { it.toDomain() } }

    override fun isFavourite(id: String): Flow<Boolean> =
        dao.isFavourite(id)
}