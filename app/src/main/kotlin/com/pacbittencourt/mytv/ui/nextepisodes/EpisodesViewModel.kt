package com.pacbittencourt.mytv.ui.nextepisodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pacbittencourt.mytv.core.Result
import com.pacbittencourt.mytv.core.asResult
import com.pacbittencourt.mytv.database.dao.NextDao
import com.pacbittencourt.mytv.database.model.NextEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    nextDao: NextDao
) : ViewModel() {


    val showsResult: StateFlow<ShowsUiState> = nextDao.getAll()
        .asResult()
        .map {
            when (it) {
                is Result.Error -> ShowsUiState.Failed
                is Result.Loading -> ShowsUiState.Failed
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
}


sealed interface ShowsUiState {
    data class Success(val result: List<NextEntity> = emptyList()) : ShowsUiState
    data object Loading : ShowsUiState
    data object Empty : ShowsUiState
    data object Failed : ShowsUiState
}