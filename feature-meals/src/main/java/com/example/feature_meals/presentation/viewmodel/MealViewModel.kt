package com.example.feature_meals.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core_common.Resource
import com.example.core_common.model.FavouriteMeal
import com.example.feature_meals.domain.usecase.GetFavouritesUseCase
import com.example.feature_meals.domain.usecase.GetMealsUseCase
import com.example.feature_meals.domain.usecase.ToggleFavouriteUseCase
import com.example.feature_meals.presentation.uistate.MealUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val getMealsUseCase: GetMealsUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val getFavouritesUseCase: GetFavouritesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<MealUiState>(MealUiState.Idle)
    val state: StateFlow<MealUiState> = _state.asStateFlow()

    val favourites = getFavouritesUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun load(category: String = "Seafood") {
        viewModelScope.launch {
            _state.value = MealUiState.Loading
            _state.value = when (val result = getMealsUseCase(category)) {
                is Resource.Success -> MealUiState.Success(result.data)
                is Resource.Error   -> MealUiState.Error(result.message)
                is Resource.Loading -> MealUiState.Loading
            }
        }
    }

    fun toggleFavourite(meal: FavouriteMeal, isFavourite: Boolean) {
        viewModelScope.launch {
            toggleFavouriteUseCase(meal, isFavourite)
        }
    }
}