package com.example.shalawat.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            // Placeholder for Detail Screen (Phase 8)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Detail Screen (Phase 8 Placeholder)")
            }
        }

        composable<AddEditRoute> {
            // Placeholder for Add/Edit Screen (Phase 9)
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Add/Edit Screen (Phase 9 Placeholder)")
            }
        }
    }
}
