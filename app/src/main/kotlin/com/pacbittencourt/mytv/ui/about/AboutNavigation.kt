package com.pacbittencourt.mytv.ui.about

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val aboutRoute = "about"

fun NavController.navigateToAbout(navOptions: NavOptions? = null) {
    this.navigate(aboutRoute, navOptions)
}

fun NavGraphBuilder.aboutScreen() {
    composable(route = aboutRoute) {
        AboutScreen()
    }
}