package com.pacbittencourt.mytv.ui.nextepisodes

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pacbittencourt.mytv.R
import com.pacbittencourt.mytv.data.model.NextEpisodeModel
import com.pacbittencourt.mytv.ui.components.EmptyState
import com.pacbittencourt.mytv.ui.components.FailedState
import com.pacbittencourt.mytv.ui.components.LoadingState

@Composable
fun EpisodesScreen(
    viewModel: EpisodesViewModel = hiltViewModel()
) {
    val showsResult by viewModel.showsResult.collectAsState()
    when (showsResult) {
        ShowsUiState.Empty -> {
            EmptyState(
                listOf(
                    stringResource(R.string.episodes_no_show_yet),
                    stringResource(R.string.episodes_go_to_search)
                )
            )
        }

        ShowsUiState.Failed -> {
            FailedState()
        }

        ShowsUiState.Loading -> {
            LoadingState()
        }

        is ShowsUiState.Success -> {
            ShowsResult(showsResult as ShowsUiState.Success) { showId, episodeId ->
                viewModel.markEpisodeAsWatched(showId, episodeId)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowsResult(showsResult: ShowsUiState.Success, watchedShowClick: (Int, Int) -> Unit) {
    val data = showsResult.result
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 18.dp, vertical = 6.dp),
            text = stringResource(R.string.episodes_watch_next),
            fontSize = TextUnit(12f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary
        )
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = data, key = { it.showId }) {
                Row(modifier = Modifier.animateItemPlacement()) {
                    NextEpisodeItem(it, watchedShowClick)
                }
            }
        }
    }
}

@Composable
private fun NextEpisodeItem(
    nextEpisode: NextEpisodeModel,
    watchedShowClick: (Int, Int) -> Unit = { _, _ -> }
) {
    AnimatedContent(
        targetState = nextEpisode,
        transitionSpec = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(600)
            ) togetherWith
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.End,
                        animationSpec = tween(600)
                    )
        },
        contentAlignment = Alignment.Center,
        label = ""
    ) { targetState ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    modifier = Modifier.width(100.dp),
                    model = targetState.image,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    error = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentScale = ContentScale.FillWidth
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = targetState.showName,
                    )
                    Text(
                        text = "${targetState.season}x${targetState.episodeInSeason}",
                    )
                    Text(
                        text = targetState.episodeName,
                    )
                }
                IconButton(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    onClick = {
                        watchedShowClick(targetState.showId, targetState.episodeId)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "add",
                    )
                }
            }
        }
    }
}