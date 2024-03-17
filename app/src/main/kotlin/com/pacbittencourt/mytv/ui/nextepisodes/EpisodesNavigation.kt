package com.pacbittencourt.mytv.ui.nextepisodes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val episodesRoute = "episodes"

fun NavController.navigateToTvShows(navOptions: NavOptions? = null) {
    this.navigate(episodesRoute, navOptions)
}

fun NavGraphBuilder.tvShowsScreen(viewModel: EpisodesViewModel) {
    composable(route = episodesRoute) {
        EpisodesScreen(viewModel)
    }
}