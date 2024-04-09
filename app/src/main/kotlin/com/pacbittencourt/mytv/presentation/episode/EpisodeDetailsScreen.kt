package com.pacbittencourt.mytv.presentation.episode

import android.text.SpannableStringBuilder
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.pacbittencourt.mytv.presentation.components.LoadingState

@Composable
fun EpisodeDetailsScreen(
    viewModel: EpisodeDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    when (val s = state) {
        EpisodeDetailsState.Error -> {

        }
        EpisodeDetailsState.Loading -> LoadingState()
        is EpisodeDetailsState.Success -> {
            Column {
                Text(text = "season: ${s.episode.season}")
                Text(text = "number: ${s.episode.number}")
                val string = SpannableStringBuilder(s.episode.summary)
                val finalString = HtmlCompat.fromHtml(string.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT)
                Text(text = "summary: $finalString")
                Text(text = "name: ${s.episode.name}")
            }
        }
    }
}