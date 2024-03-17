package com.pacbittencourt.mytv.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.pacbittencourt.mytv.navigation.MyTvNavHost
import com.pacbittencourt.mytv.ui.nextepisodes.EpisodesViewModel
import com.pacbittencourt.mytv.ui.nextepisodes.ShowsUiState
import com.pacbittencourt.mytv.ui.theme.MyTVTheme
import com.pacbittencourt.mytv.ui.theme.MyTvBottomAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val episodesViewModel: EpisodesViewModel by viewModels<EpisodesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                episodesViewModel.showsResult.value is ShowsUiState.Loading
            }
        }
        setContent {
            MyTvApp(episodesViewModel)
        }
    }
}

@Composable
private fun MyTvApp(viewModel: EpisodesViewModel) {
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
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel
            )
        }
    }
}

