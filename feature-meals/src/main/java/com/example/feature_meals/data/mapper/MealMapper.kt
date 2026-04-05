package com.example.feature_meals.data.mapper

import com.example.feature_meals.data.dto.MealDto
import com.example.feature_meals.domain.model.Meal


fun MealDto.toDomain(): Meal {
    return Meal(
        id = id ?: "",
        name = name ?: "Unknown",
        thumbnailUrl = thumbnailUrl ?: "",
        instructions = instructions ?: "No instructions available",
        category = category ?: "General"
    )
}
