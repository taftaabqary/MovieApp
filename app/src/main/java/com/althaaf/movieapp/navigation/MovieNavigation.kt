package com.althaaf.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.althaaf.movieapp.screen.DetailScreen
import com.althaaf.movieapp.screen.HomeScreen

@Composable
fun MovieNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController,
        modifier = modifier,
        startDestination = MovieScreen.HOME.name) {
        composable(MovieScreen.HOME.name) {
            HomeScreen(navController = navController)
        }

        composable(MovieScreen.DETAIL.name + "/{movie}",
            arguments = listOf(navArgument("movie") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->

            DetailScreen(navController = navController, data = navBackStackEntry.arguments?.getString("movie"))
        }
    }
}