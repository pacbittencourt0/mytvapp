package com.pacbittencourt.mytv.presentation.nextepisodes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val episodesRoute = "episodes"

fun NavController.navigateToTvShows(navOptions: NavOptions? = null) {
    this.navigate(episodesRoute, navOptions)
}

fun NavGraphBuilder.tvShowsScreen(onEpisodeClick: (Int) -> Unit, viewModel: EpisodesViewModel) {
    composable(route = episodesRoute) {
        EpisodesScreen(onEpisodeClick, viewModel)
    }
}