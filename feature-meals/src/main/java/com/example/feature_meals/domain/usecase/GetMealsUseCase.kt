package com.example.feature_meals.domain.usecase

import com.example.core_common.Resource
import com.example.feature_meals.domain.model.Meal
import com.example.feature_meals.domain.repository.MealRepository
import javax.inject.Inject

class GetMealsUseCase @Inject constructor(
    private val repository: MealRepository
) {

    // suspend used for cooroutine so donot effect main thread
    // treat class as constructor
    suspend operator fun invoke(category: String): Resource<List<Meal>> =
        repository.getMealsByCategory(category)
}