package com.pacbittencourt.mytv.presentation.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val searchRoute = "search"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

fun NavGraphBuilder.searchScreen() {
    composable(route = searchRoute) {
        SearchScreen()
    }
}