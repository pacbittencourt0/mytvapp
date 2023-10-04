package com.pacbittencourt.mytv.ui.theme

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.pacbittencourt.mytv.navigation.MyTvBottomMenuItem
import com.pacbittencourt.mytv.navigation.bottomBarDestinations
import com.pacbittencourt.mytv.navigation.navigateSingleTopTo

@Composable
fun MyTvBottomAppBar(
    navHostController: NavHostController,
    currentScreen: MyTvBottomMenuItem
) {
    BottomAppBar {
        for (item in bottomBarDestinations) {
            NavigationBarItem(
                label = {
                    Text(text = stringResource(item.label))
                },
                selected = currentScreen == item,
                onClick = {
                    navHostController.navigateSingleTopTo(item.route)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.contentDesc)
                })
        }
    }
}