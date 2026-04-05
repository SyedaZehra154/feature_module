package com.example.authentication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.feature_auth.presentation.AuthViewModel
import com.example.feature_auth.presentation.screens.LoginScreen
import com.example.feature_auth.presentation.screens.SignupScreen
import com.example.feature_meals.presentation.MealListScreen
import com.example.feature_meals.presentation.MealDetailScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        // 1️⃣ Login Screen
        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                onNavigateToSignup = {
                    navController.navigate("signup")
                },
                onLoginSuccess = {
                    navController.navigate("meal_list") {
                        popUpTo("login") { inclusive = true } // remove login from backstack
                    }
                }
            )
        }

        // 2️⃣ Signup Screen
        composable("signup") {
            SignupScreen(
                viewModel = authViewModel,
                onSignupSuccess = {
                    navController.navigate("meal_list") {
                        popUpTo("signup") { inclusive = true } // remove signup from backstack
                    }
                }
            )
        }

        // 3️⃣ Meal List Screen
        composable("meal_list") {
            MealListScreen(
                onMealClick = { id ->
                    navController.navigate("meal_detail/$id")
                }
            )
        }

        // 4️⃣ Meal Detail Screen
        composable(
            route = "meal_detail/{mealId}",
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
    }
}