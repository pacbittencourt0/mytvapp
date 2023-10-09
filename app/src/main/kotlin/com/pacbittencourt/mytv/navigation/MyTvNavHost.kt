package com.pacbittencourt.mytv.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.pacbittencourt.mytv.ui.profile.profileScreen
import com.pacbittencourt.mytv.ui.search.searchRoute
import com.pacbittencourt.mytv.ui.search.searchScreen
import com.pacbittencourt.mytv.ui.tvshows.tvShowsScreen

@Composable
fun MyTvNavHost(navController: NavHostController, modifier: Modifier) {
    NavHost(modifier = modifier, navController = navController, startDestination = searchRoute) {
        tvShowsScreen()
        searchScreen()
        profileScreen()
    }
}