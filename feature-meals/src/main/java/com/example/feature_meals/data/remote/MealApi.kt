package com.example.feature_meals.data.remote

import com.example.feature_meals.data.dto.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query


// Interface that defines API endpoints
interface MealApi {

    //  GET request to fetch meals by category
    // Example: filter.php?c=Seafood
    @GET("filter.php")
    suspend fun getMealsByCategory(

        //  Query parameter "c" is sent in URL
        // Example: ?c=Seafood
        @Query("c") category: String

    ): MealResponse
    //  Response will be mapped into MealResponse object


    //  GET request to fetch meal details by ID
    // Example: lookup.php?i=52772
    @GET("lookup.php")
    suspend fun getMealDetails(

        //  Query parameter "i" is meal ID
        @Query("i") mealId: String

    ): MealResponse
    //  Response contains detailed meal info
}

