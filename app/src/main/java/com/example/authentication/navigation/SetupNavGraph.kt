// app/src/main/java/com/example/authentication/navigation/SetupNavGraph.kt
package com.example.authentication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.feature_auth.nav_graph.AuthRoutes
import com.example.feature_auth.nav_graph.authNavGraph
import com.example.feature_meals.navigation.MealRoutes
import com.example.feature_meals.navigation.mealNavGraph
import com.example.feature_auth.presentation.AuthViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController    = navController,
        startDestination = AuthRoutes.ROOT
    ) {

        //  plugs in auth nav graph
        authNavGraph(
            navController = navController,
            authViewModel = authViewModel,
            onLoginSuccess = {
                navController.navigate(MealRoutes.ROOT) {
                    popUpTo(AuthRoutes.ROOT) { inclusive = true } // clears auth from backstack
                }
            }
        )

        //  plugs in meal nav graph
        mealNavGraph(
            navController = navController
        )
    }
}