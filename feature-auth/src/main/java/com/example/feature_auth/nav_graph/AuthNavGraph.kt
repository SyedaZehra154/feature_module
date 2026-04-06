package com.example.feature_auth.nav_graph
//

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.feature_auth.presentation.AuthViewModel
import com.example.feature_auth.presentation.screens.LoginScreen
import com.example.feature_auth.presentation.screens.SignupScreen

//  route constants — avoids hardcoded strings
object AuthRoutes {
    const val ROOT       = "auth_root"
    const val LOGIN      = "login"
    const val SIGNUP     = "signup"
}

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    onLoginSuccess: () -> Unit
) {
    navigation(
        startDestination = AuthRoutes.LOGIN,
        route            = AuthRoutes.ROOT
    ) {
        composable(AuthRoutes.LOGIN) {
            LoginScreen(
                viewModel          = authViewModel,
                onNavigateToSignup = {
                    navController.navigate(AuthRoutes.SIGNUP)
                },
                onLoginSuccess     = onLoginSuccess   //  passed up to app
            )
        }

        composable(AuthRoutes.SIGNUP) {
            SignupScreen(
                viewModel       = authViewModel,
                onSignupSuccess = onLoginSuccess      //  same callback
            )
        }
    }
}