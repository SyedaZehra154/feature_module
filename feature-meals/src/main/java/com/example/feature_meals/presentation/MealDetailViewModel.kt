package com.example.feature_meals.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_common.Resource
import com.example.feature_meals.domain.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    private val _state = MutableStateFlow<MealDetailUiState>(MealDetailUiState.Idle)
    val state = _state.asStateFlow()

    fun getMealDetails(id: String) {
        viewModelScope.launch {
            _state.value = MealDetailUiState.Loading

            // Fixed exhaustive 'when' block
            when (val result = repository.getMealDetails(id)) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.value = MealDetailUiState.Success(it)
                    } ?: run {
                        _state.value = MealDetailUiState.Error("Meal details not found")
                    }
                }
                is Resource.Error -> {
                    _state.value = MealDetailUiState.Error(result.message ?: "An error occurred")
                }
                is Resource.Loading -> {
                    _state.value = MealDetailUiState.Loading
                }
            }
        }
    }
}