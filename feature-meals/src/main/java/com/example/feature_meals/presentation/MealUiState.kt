package com.example.feature_meals.presentation

import com.example.feature_meals.domain.model.Meal

sealed class MealUiState {
    object Idle    : MealUiState()
    object Loading : MealUiState()
    data class Success(val meals: List<Meal>) : MealUiState()
    data class Error(val message: String)     : MealUiState()
}