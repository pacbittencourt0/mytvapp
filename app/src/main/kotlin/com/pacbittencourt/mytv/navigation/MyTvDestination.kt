package com.pacbittencourt.mytv.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material.icons.sharp.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.pacbittencourt.mytv.R

enum class TopLevelDestination(
    val icon: ImageVector,
    val label: Int,
    val contentDesc: String,
) {
    TV_SHOWS(
        icon = Icons.Sharp.Tv,
        label = R.string.bottom_menu_tv_shows,
        contentDesc = "tv shows"
    ),
    SEARCH(
        icon = Icons.Sharp.Search,
        label = R.string.bottom_menu_search,
        contentDesc = "search",
    ),
    PROFILE(
        icon = Icons.Sharp.AccountCircle,
        label = R.string.bottom_menu_profile,
        contentDesc = "profile"
    )
}