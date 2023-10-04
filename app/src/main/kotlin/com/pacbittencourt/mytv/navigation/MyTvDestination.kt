package com.pacbittencourt.mytv.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material.icons.sharp.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.pacbittencourt.mytv.R

interface MyTvDestination {
    val route: String
}

interface MyTvBottomMenuItem : MyTvDestination {
    val icon: ImageVector
    val label: Int
    val contentDesc: String
}

object TvShows : MyTvBottomMenuItem {
    override val route: String = "tvshows"
    override val icon: ImageVector = Icons.Sharp.Tv
    override val label: Int = R.string.bottom_menu_tv_shows
    override val contentDesc: String = "tv shows"
}

object Search : MyTvBottomMenuItem {
    override val route: String = "search"
    override val icon: ImageVector = Icons.Sharp.Search
    override val label: Int = R.string.bottom_menu_search
    override val contentDesc: String = "search"
}

object Profile : MyTvBottomMenuItem {
    override val route: String = "profile"
    override val icon: ImageVector = Icons.Sharp.AccountCircle
    override val label: Int = R.string.bottom_menu_profile
    override val contentDesc: String = "profile"
}


val bottomBarDestinations = listOf(TvShows, Search, Profile)