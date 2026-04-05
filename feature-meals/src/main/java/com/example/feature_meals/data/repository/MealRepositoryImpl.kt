package com.example.feature_meals.data.repository

import com.example.core_common.Resource
import com.example.feature_meals.data.mapper.toDomain
import com.example.feature_meals.data.remote.MealApi
import com.example.feature_meals.domain.model.Meal
import com.example.feature_meals.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val api: MealApi
) : MealRepository {

    override suspend fun getMealsByCategory(category: String): Resource<List<Meal>> {
        return try {
            val response = api.getMealsByCategory(category)
            val meals = response.meals?.map { it.toDomain() } ?: emptyList()
            Resource.Success(meals)
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Something went wrong")
        }
    }

    override suspend fun getMealDetails(mealId: String): Resource<Meal> {
        return try {
            val response = api.getMealDetails(mealId)
            val mealDto = response.meals?.firstOrNull()

            if (mealDto != null) {
                Resource.Success(mealDto.toDomain())
            } else {
                Resource.Error("Meal not found")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }
}