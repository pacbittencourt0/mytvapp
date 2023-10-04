package com.pacbittencourt.mytv.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pacbittencourt.mytv.SearchScreen

@Composable
fun MyTvNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(navController = navController, startDestination = TvShows.route) {
        composable(route = TvShows.route) {

        }
        composable(route = Search.route) {
            SearchScreen(modifier)
        }
        composable(route = Profile.route) {

        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

