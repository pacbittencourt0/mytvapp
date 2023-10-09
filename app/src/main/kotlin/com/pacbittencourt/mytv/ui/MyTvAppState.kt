package com.pacbittencourt.mytv.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import androidx.tracing.trace
import com.pacbittencourt.mytv.navigation.TopLevelDestination
import com.pacbittencourt.mytv.ui.profile.navigateToProfile
import com.pacbittencourt.mytv.ui.search.navigateToSearch
import com.pacbittencourt.mytv.ui.tvshows.navigateToTvShows

class MyTvAppState(
    val navController: NavHostController
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    fun navigateToDestination(destination: TopLevelDestination) {
        trace("Navigation: ${destination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (destination) {
                TopLevelDestination.TV_SHOWS -> navController.navigateToTvShows(topLevelNavOptions)
                TopLevelDestination.SEARCH -> navController.navigateToSearch(topLevelNavOptions)
                TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
            }
        }
    }
}