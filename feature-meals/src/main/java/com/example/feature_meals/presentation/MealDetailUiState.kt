package com.example.feature_meals.presentation

import com.example.feature_meals.domain.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// ADD THIS HERE (Above your ViewModel class)
sealed class MealDetailUiState {
    object Idle : MealDetailUiState()
    object Loading : MealDetailUiState()
    data class Success(val meal: Meal) : MealDetailUiState()
    data class Error(val message: String) : MealDetailUiState()
}

