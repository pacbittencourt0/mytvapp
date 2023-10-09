package com.pacbittencourt.mytv.ui.tvshows

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val tvShowsRoute = "tv_shows"

fun NavController.navigateToTvShows(navOptions: NavOptions? = null) {
    this.navigate(tvShowsRoute, navOptions)
}

fun NavGraphBuilder.tvShowsScreen() {
    composable(route = tvShowsRoute) {
        Text(text = "TV Shows Screen")
    }
}