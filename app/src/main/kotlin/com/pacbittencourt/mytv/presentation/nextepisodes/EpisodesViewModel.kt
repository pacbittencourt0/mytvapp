package com.pacbittencourt.mytv.presentation.nextepisodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pacbittencourt.mytv.domain.Result
import com.pacbittencourt.mytv.domain.asResult
import com.pacbittencourt.mytv.data.model.NextEpisodeModel
import com.pacbittencourt.mytv.domain.usecase.GetNextEpisodesUseCase
import com.pacbittencourt.mytv.domain.usecase.MarkEpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    nextEpisodesUseCase: GetNextEpisodesUseCase,
    private val markEpisodeUseCase: MarkEpisodeUseCase
) : ViewModel() {

    val showsResult: StateFlow<ShowsUiState> = nextEpisodesUseCase()
        .asResult()
        .map {
            when (it) {
                is Result.Error -> ShowsUiState.Failed
                is Result.Loading -> ShowsUiState.Loading
                is Result.Success -> {
                    if (it.data.isEmpty()) {
                        ShowsUiState.Empty
                    } else {
                        ShowsUiState.Success(it.data)
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ShowsUiState.Loading
        )


    fun markEpisodeAsWatched(showId: Int, episodeId: Int) {
        viewModelScope.launch {
            markEpisodeUseCase(showId, episodeId)
        }
    }
}


sealed interface ShowsUiState {
    data class Success(val result: List<NextEpisodeModel> = emptyList()) : ShowsUiState
    data object Loading : ShowsUiState
    data object Empty : ShowsUiState
    data object Failed : ShowsUiState
}