package com.pacbittencourt.mytv.ui.nextepisodes

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pacbittencourt.mytv.R
import com.pacbittencourt.mytv.database.model.NextEntity

@Composable
fun EpisodesScreen(
    viewModel: EpisodesViewModel = hiltViewModel()
) {
    val showsResult by viewModel.showsResult.collectAsState()
    Column {
        when(showsResult) {
            ShowsUiState.Empty -> {}
            ShowsUiState.Failed -> {}
            ShowsUiState.Loading -> {}
            is ShowsUiState.Success -> ShowsResult(showsResult as ShowsUiState.Success)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ShowsResult(showsResult: ShowsUiState.Success) {
    val data = showsResult.result
    LazyColumn {
        items(items = data) {
            Row(Modifier.animateItemPlacement()) {
                NextEpisodeItem(it)
            }
        }
    }
}

@Preview
@Composable
private fun NextEpisodeItem(
    episode: NextEntity = NextEntity(1,2),
    watchedShowClick: () -> Unit = {}
) {
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
                model = "show.imageMediumUrl",
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
                Text(text = "${episode.showId}")
                Text(text = "${episode.episodeId}")
                Text(text = "3x10")
            }
            IconButton(
                modifier = Modifier.padding(horizontal = 8.dp),
                onClick = { }
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "add")
            }
        }
    }
}