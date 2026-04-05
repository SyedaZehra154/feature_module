//package com.example.authentication.presentation.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.authentication.presentation.dashboard.DashboardScreen
//import com.example.authentication.presentation.login.LoginScreen
//import com.example.authentication.presentation.signup.SignupScreen
//
//@Composable
//fun AppNavGraph() {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        startDestination = AppRoute.Login
//    ) {
//        composable<AppRoute.Login> {
//            LoginScreen(
//                onLoginSuccess = {
//                    navController.navigate(AppRoute.Dashboard) {
//                        popUpTo<AppRoute.Login> { inclusive = true }
//                    }
//                },
//                onNavigateSignup  = {
//                    navController.navigate(AppRoute.Signup)
//                }
//            )
//        }
//        composable<AppRoute.Signup> {
//            SignupScreen(
//                onSignupSuccess = {
//                    navController.navigate(AppRoute.Login) {
//                        popUpTo<AppRoute.Signup> { inclusive = true }
//                    }
//                },
//                onBackToLogin = {
//                    navController.navigate(AppRoute.Login){
//                        popUpTo<AppRoute.Signup>{ inclusive=true }
//                    }
//
//                }
//
//
//            )
//        }
//        composable<AppRoute.Dashboard> {
//            DashboardScreen(
//   onLogoutClick = {
//       navController.navigate(AppRoute.Login){
//           popUpTo<AppRoute.Dashboard>{ inclusive=true }
//       }
//
//   }
//            )
//        }
//    }
//
//
//}