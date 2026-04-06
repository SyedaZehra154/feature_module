package com.example.feature_meals.domain.model

data class Meal(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val instructions: String?,
    val category: String?
)