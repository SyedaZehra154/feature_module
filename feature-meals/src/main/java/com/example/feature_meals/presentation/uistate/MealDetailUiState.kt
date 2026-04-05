package com.example.feature_meals.presentation.uistate

import com.example.feature_meals.domain.model.Meal

// ADD THIS HERE (Above your ViewModel class)
sealed class MealDetailUiState {
    object Idle : MealDetailUiState()
    object Loading : MealDetailUiState()
    data class Success(val meal: Meal) : MealDetailUiState()
    data class Error(val message: String) : MealDetailUiState()
}