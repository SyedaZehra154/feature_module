package com.example.feature_meals.domain.model

//// domain/model/Meal.kt
//data class Meal(
//    val id: String,
//    val name: String,
//    val category: String = "",
//    val thumbnailUrl: String
//)

data class Meal(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val instructions: String?,
    val category: String?
)