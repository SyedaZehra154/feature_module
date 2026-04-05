//package com.example.feature_auth.nav_graph
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.*
//import androidx.navigation.compose.rememberNavController
//import com.example.feature_auth.presentation.screens.LoginScreen
//import com.example.feature_auth.presentation.screens.SignupScreen
//import com.yourpackage.feature_auth.presentation.*
//
//@Composable
//fun AuthNavGraph(viewModel: AuthViewModel) {
//
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "login") {
//
//        composable("login") {
//            LoginScreen(
//                viewModel = viewModel,
//                onNavigateToSignup = {
//                    navController.navigate("signup")
//                }
//            )
//        }
//
//        composable("signup") {
//            SignupScreen(
//                viewModel = viewModel,
//                onSignupSuccess = {
//                    navController.popBackStack() // go back to login
//                }
//            )
//        }
//    }
//}