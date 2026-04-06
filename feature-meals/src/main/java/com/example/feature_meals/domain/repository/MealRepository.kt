package com.example.feature_meals.domain.repository

import com.example.core_common.Resource
import com.example.feature_meals.domain.model.Meal

interface MealRepository {
    suspend fun getMealsByCategory(category: String): Resource<List<Meal>>

    // Add this line
    suspend fun getMealDetails(mealId: String): Resource<Meal>
}