package com.pacbittencourt.mytv.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pacbittencourt.mytv.ui.about.aboutScreen
import com.pacbittencourt.mytv.ui.nextepisodes.episodesRoute
import com.pacbittencourt.mytv.ui.nextepisodes.tvShowsScreen
import com.pacbittencourt.mytv.ui.search.searchScreen

@Composable
fun MyTvNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(modifier = modifier, navController = navController, startDestination = episodesRoute) {
        tvShowsScreen()
        searchScreen()
        aboutScreen()
    }
}
