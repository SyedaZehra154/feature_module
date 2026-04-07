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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

//  Tells Hilt that this ViewModel can receive dependencies
@HiltViewModel
class MealViewModel @Inject constructor(

    //  Use case to fetch meals from repository
    private val getMealsUseCase: GetMealsUseCase,

    //  Use case to add/remove favourites
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,

    //  Use case to get list of favourite meals
    private val getFavouritesUseCase: GetFavouritesUseCase

) : ViewModel() {

    //  Internal mutable state (can be updated inside ViewModel)
    private val _state = MutableStateFlow<MealUiState>(MealUiState.Idle)

    //  Public read-only state exposed to UI
    val state: StateFlow<MealUiState> = _state.asStateFlow()

    //  Flow of favourite meals converted into StateFlow
    val favourites = getFavouritesUseCase()
        .stateIn(
            viewModelScope,                 // lifecycle scope of ViewModel
            SharingStarted.WhileSubscribed(5000), // keeps data active while subscribed
            emptyList()                     // initial value
        )

    //  Function to load meals
    fun load(category: String = "Seafood") {
        viewModelScope.launch { // coroutine tied to ViewModel lifecycle

            _state.value = MealUiState.Loading // set loading state

            // Call use case and handle result
            _state.value = when (val result = getMealsUseCase(category)) {

                is Resource.Success -> MealUiState.Success(result.data)

                is Resource.Error   -> MealUiState.Error(result.message)

                is Resource.Loading -> MealUiState.Loading
            }
        }
    }

    //  Function to add/remove favourite meal
    fun toggleFavourite(meal: FavouriteMeal, isFavourite: Boolean) {
        viewModelScope.launch {

            // Call use case to update favourite status
            toggleFavouriteUseCase(meal, isFavourite)
        }
    }
}