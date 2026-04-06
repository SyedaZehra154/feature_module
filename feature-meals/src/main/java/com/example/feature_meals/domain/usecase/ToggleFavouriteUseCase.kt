package com.example.feature_meals.domain.usecase


import com.example.core_common.model.FavouriteMeal
import com.example.core_common.repository.FavouriteRepository
import javax.inject.Inject

class ToggleFavouriteUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    suspend operator fun invoke(meal: FavouriteMeal, isFavourite: Boolean) {
        if (isFavourite) repository.deleteFavourite(meal)
        else repository.insertFavourite(meal)
    }
}

// feature-meals/domain/use_case/GetFavouritesUseCase.kt
class GetFavouritesUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke() = repository.getAllFavourites()
}