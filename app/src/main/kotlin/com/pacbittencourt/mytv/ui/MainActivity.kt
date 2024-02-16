package com.pacbittencourt.mytv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.pacbittencourt.mytv.navigation.MyTvNavHost
import com.pacbittencourt.mytv.ui.theme.MyTVTheme
import com.pacbittencourt.mytv.ui.theme.MyTvBottomAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTvApp()
        }
    }
}

@Composable
private fun MyTvApp() {
    MyTVTheme {
        val appState = MyTvAppState(navController = rememberNavController())
        Scaffold(
            bottomBar = {
                MyTvBottomAppBar(
                    onItemClick = appState::navigateToDestination,
                    currentDestination = appState.currentDestination
                )
            }
        ) { paddingValues: PaddingValues ->
            MyTvNavHost(
                navController = appState.navController,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

