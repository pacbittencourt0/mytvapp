package com.pacbittencourt.mytv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pacbittencourt.mytv.navigation.MyTvNavHost
import com.pacbittencourt.mytv.navigation.TvShows
import com.pacbittencourt.mytv.navigation.bottomBarDestinations
import com.pacbittencourt.mytv.ui.theme.MyTVTheme
import com.pacbittencourt.mytv.ui.theme.MyTvBottomAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTvApp()
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyTvApp() {
    MyTVTheme {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            bottomBarDestinations.find { it.route == currentDestination?.route } ?: TvShows
        Scaffold(
            bottomBar = { MyTvBottomAppBar(navController, currentScreen) }
        ) { paddingValues: PaddingValues ->
            MyTvNavHost(
                navController = navController,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

