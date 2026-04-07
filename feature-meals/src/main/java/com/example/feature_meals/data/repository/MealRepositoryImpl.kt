package com.example.feature_meals.data.repository

import com.example.core_common.Resource
import com.example.feature_meals.data.mapper.toDomain
import com.example.feature_meals.data.remote.MealApi
import com.example.feature_meals.domain.model.Meal
import com.example.feature_meals.domain.repository.MealRepository
import javax.inject.Inject

// Repository implementation
class MealRepositoryImpl @Inject constructor(
    private val api: MealApi // Dependency injected by Hilt
) : MealRepository {

    // 🔹 Get meals by category
    override suspend fun getMealsByCategory(category: String): Resource<List<Meal>> {
        return try {

            // Call API
            val response = api.getMealsByCategory(category)

            // Convert DTO → Domain model

            val meals = response.meals?.map { it.toDomain() } ?: emptyList()

            // Return success
            Resource.Success(meals)

        } catch (e: Exception) {

            // Handle error
            Resource.Error(e.localizedMessage ?: "Something went wrong")
        }
    }

    //  Get meal details by ID
    override suspend fun getMealDetails(mealId: String): Resource<Meal> {
        return try {

            // Call API
            val response = api.getMealDetails(mealId)

            // Get first meal from list
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