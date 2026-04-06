package com.example.feature_meals.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.feature_meals.presentation.ui.FavouriteScreen
import com.example.feature_meals.presentation.ui.MealDetailScreen
import com.example.feature_meals.presentation.ui.MealListScreen

//  route constants
object MealRoutes {
    const val ROOT       = "meal_root"
    const val MEAL_LIST  = "meal_list"
    const val MEAL_DETAIL = "meal_detail/{mealId}"
    const val FAVOURITES = "favourites"

    fun mealDetail(mealId: String) = "meal_detail/$mealId"
}

fun NavGraphBuilder.mealNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = MealRoutes.MEAL_LIST,
        route            = MealRoutes.ROOT
    ) {
        composable(MealRoutes.MEAL_LIST) {
            MealListScreen(
                onMealClick            = { id ->
                    navController.navigate(MealRoutes.mealDetail(id))
                },
                onNavigateToFavourites = {
                    navController.navigate(MealRoutes.FAVOURITES)
                }
            )
        }

        composable(
            route     = MealRoutes.MEAL_DETAIL,
            arguments = listOf(
                navArgument("mealId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId")
            MealDetailScreen(
                mealId = mealId,
                onBack = { navController.popBackStack() }
            )
        }

        composable(MealRoutes.FAVOURITES) {
            FavouriteScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}