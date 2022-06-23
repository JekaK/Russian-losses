package com.russialoses.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.russialoses.app.presentation.feature.home.Home
import com.russialoses.app.presentation.feature.home.presentation.HomeViewModel
import com.russialoses.app.presentation.feature.splashscreen.AnimatedSplashScreen
import com.russialoses.app.presentation.feature.splashscreen.presentation.SplashScreenViewModel

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Splash.route) {
            val viewModel: SplashScreenViewModel = hiltViewModel()
            AnimatedSplashScreen(
                navHostController = navController,
                viewModel = viewModel
            )
        }
        composable(route = Screen.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            Home(viewModel = viewModel)
        }
    }
}