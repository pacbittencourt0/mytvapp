package com.pacbittencourt.mytv.presentation.episode

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val argEpisodeId = "episodeId"
const val route = "episodes/episodeDetails"
const val episodeDetailsRoute = "$route/{$argEpisodeId}"

fun NavController.navigateToEpisodeDetails(episodeId: Int, navOptions: NavOptions? = null) =
    this.navigate("$route/$episodeId", navOptions)

fun NavGraphBuilder.episodeDetailsScreen() {
    composable(
        route = episodeDetailsRoute,
        arguments = listOf(navArgument(argEpisodeId) { type = NavType.IntType })
    ) {
        EpisodeDetailsScreen()
    }
}