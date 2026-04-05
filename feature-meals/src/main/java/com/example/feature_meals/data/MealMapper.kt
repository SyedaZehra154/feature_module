//package com.example.feature_meals.data
//
//import com.example.feature_meals.data.dto.MealDto
//import com.example.feature_meals.domain.model.Meal
//
//
//fun MealDto.toDomain(): Meal {
//    return Meal(
//        id = id ?: "",
//        name = name ?: "Unknown Meal",
//        // Using the new fields you added to MealDto
//        category = category ?: "General",
//        instructions = instructions ?: "No instructions provided.",
//        thumbnailUrl = thumbnailUrl.orEmpty()
//    )
//}
//fun MealDto.MealDtotoDomain() = Meal(
//    id = id,
//    name = name,
//    category = "",
//    thumbnailUrl = thumbnailUrl.orEmpty()
//)