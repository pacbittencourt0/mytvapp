package com.pacbittencourt.mytv.domain.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pacbittencourt.mytv.presentation.episode.episodeDetailsScreen
import com.pacbittencourt.mytv.presentation.episode.navigateToEpisodeDetails
import com.pacbittencourt.mytv.presentation.about.aboutScreen
import com.pacbittencourt.mytv.presentation.nextepisodes.EpisodesViewModel
import com.pacbittencourt.mytv.presentation.nextepisodes.episodesRoute
import com.pacbittencourt.mytv.presentation.nextepisodes.tvShowsScreen
import com.pacbittencourt.mytv.presentation.search.searchScreen

@Composable
fun MyTvNavHost(
    navController: NavHostController,
    modifier: Modifier,
    viewModel: EpisodesViewModel
) {
    NavHost(modifier = modifier, navController = navController, startDestination = episodesRoute) {
        tvShowsScreen(navController::navigateToEpisodeDetails, viewModel)
        searchScreen()
        aboutScreen()
        episodeDetailsScreen()
    }
}
