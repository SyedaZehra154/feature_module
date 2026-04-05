package com.example.feature_meals.data.remote

import com.example.feature_meals.data.dto.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    // Existing: Get list of meals by category (e.g., "Seafood")
    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ): MealResponse

    // NEW: Get full details of a specific meal by its ID
    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") mealId: String
    ): MealResponse
}




//package com.example.feature_meals.data.remote
//
//import com.example.feature_meals.data.dto.MealResponse
//import retrofit2.http.GET
//import retrofit2.http.Query
//
//interface MealApi {
//
//    @GET("filter.php")
//    suspend fun getMealsByCategory(
//        @Query("c") category: String
//    ): MealResponse
//}