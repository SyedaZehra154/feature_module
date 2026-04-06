package com.example.feature_auth.data.local.dao


import androidx.room.*
import com.example.feature_auth.data.local.entity.FavouriteMealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteMealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(meal: FavouriteMealEntity)

    @Delete
    suspend fun deleteFavourite(meal: FavouriteMealEntity)

    @Query("SELECT * FROM favourite_meals")
    fun getAllFavourites(): Flow<List<FavouriteMealEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_meals WHERE id = :id)")
    fun isFavourite(id: String): Flow<Boolean>
}