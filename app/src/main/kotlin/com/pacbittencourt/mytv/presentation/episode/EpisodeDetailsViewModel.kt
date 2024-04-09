package com.pacbittencourt.mytv.presentation.episode

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pacbittencourt.mytv.domain.Result
import com.pacbittencourt.mytv.domain.asResult
import com.pacbittencourt.mytv.data.source.remote.model.EpisodeResponse
import com.pacbittencourt.mytv.domain.usecase.GetEpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getEpisodeUseCase: GetEpisodeUseCase
) : ViewModel() {

    private val episodeId: Int = checkNotNull(savedStateHandle[argEpisodeId])

    val state: StateFlow<EpisodeDetailsState> = getEpisodeUseCase(episodeId)
        .asResult()
        .map {
            when (it) {
                Result.Loading -> EpisodeDetailsState.Loading
                is Result.Error -> EpisodeDetailsState.Error
                is Result.Success -> EpisodeDetailsState.Success(it.data)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = EpisodeDetailsState.Loading
        )
}

sealed interface EpisodeDetailsState {
    data class Success(val episode: EpisodeResponse) : EpisodeDetailsState
    data object Loading : EpisodeDetailsState
    data object Error : EpisodeDetailsState
}