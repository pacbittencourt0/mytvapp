package com.pacbittencourt.mytv.ui.theme

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.pacbittencourt.mytv.R
import com.pacbittencourt.mytv.navigation.TopLevelDestination

@Composable
fun MyTvBottomAppBar(
    onItemClick: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    BottomAppBar {
        TopLevelDestination.entries.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            NavigationBarItem(
                label = {
                    Text(text = stringResource(destination.label))
                },
                selected = selected,
                onClick = {
                    onItemClick(destination)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = colorResource(id = R.color.blue_200)
                ),
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.contentDesc
                    )
                })
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination): Boolean =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
