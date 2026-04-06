package com.example.feature_meals.data.dto

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("meals") val meals: List<MealDto>?
)

data class MealDto(
    @SerializedName("idMeal")       val id: String?,
    @SerializedName("strMeal")      val name: String?,
    @SerializedName("strMealThumb") val thumbnailUrl: String?,

    @SerializedName("strInstructions") val instructions: String?,
    @SerializedName("strCategory")     val category: String?
)

