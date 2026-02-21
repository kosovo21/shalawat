package com.example.shalawat.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shalawat.presentation.addedit.AddEditScreen
import com.example.shalawat.presentation.detail.DetailScreen
import com.example.shalawat.presentation.home.HomeScreen
import com.example.shalawat.presentation.splash.SplashScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {
        composable<SplashRoute> {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(HomeRoute) {
                        popUpTo<SplashRoute> { inclusive = true }
                    }
                }
            )
        }

        composable<HomeRoute> {
            HomeScreen(
                onNavigateToDetail = { id ->
                    navController.navigate(DetailRoute(id))
                },
                onNavigateToAddEdit = {
                    navController.navigate(AddEditRoute(null))
                }
            )
        }

        composable<DetailRoute> {
            DetailScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToEdit = { id ->
                    navController.navigate(AddEditRoute(id))
                },
                onDeleted = {
                    navController.popBackStack()
                }
            )
        }

        composable<AddEditRoute> {
            AddEditScreen(
                onNavigateBack = { navController.popBackStack() },
                onSaved = { navController.popBackStack() }
            )
        }
    }
}
