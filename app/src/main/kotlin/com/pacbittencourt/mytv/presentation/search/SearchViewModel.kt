package com.pacbittencourt.mytv.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pacbittencourt.mytv.domain.Result
import com.pacbittencourt.mytv.domain.asResult
import com.pacbittencourt.mytv.data.model.ShowModel
import com.pacbittencourt.mytv.data.repository.SearchRepository
import com.pacbittencourt.mytv.domain.usecase.AddTvShowUseCase
import com.pacbittencourt.mytv.domain.usecase.RemoveShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val addTvShowUseCase: AddTvShowUseCase,
    private val removeShowUseCase: RemoveShowUseCase
) : ViewModel() {

    private val searchQuery: MutableStateFlow<String> = MutableStateFlow("")
    val searchResult: StateFlow<SearchUiState> = searchQuery.flatMapLatest { query ->
        if (query.isNotEmpty()) {
            searchRepository.searchShow(query)
                .asResult()
                .map {
                    when (it) {
                        is Result.Error -> SearchUiState.Failed
                        is Result.Loading -> SearchUiState.Loading
                        is Result.Success -> {
                            if (it.data.isEmpty())
                                SearchUiState.Empty
                            else
                                SearchUiState.Success(it.data)

                        }
                    }
                }
        } else {
            flowOf(SearchUiState.Idle)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = SearchUiState.Loading
    )

    fun search(query: String) {
        searchQuery.value = query
    }

    fun handleShowInWatchList(show: ShowModel) {
        viewModelScope.launch {
            if (show.isAdded) {
                removeShowUseCase.invoke(show.id)
            } else {
                addTvShowUseCase.invoke(show)
            }
        }
    }
}

sealed interface SearchUiState {
    data class Success(val searchResults: List<ShowModel> = emptyList()) : SearchUiState
    data object Empty : SearchUiState
    data object Loading : SearchUiState
    data object Idle : SearchUiState
    data object Failed : SearchUiState
}