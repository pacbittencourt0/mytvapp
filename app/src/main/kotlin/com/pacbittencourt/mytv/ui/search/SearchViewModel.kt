package com.pacbittencourt.mytv.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pacbittencourt.mytv.data.repository.SearchRepository
import com.pacbittencourt.mytv.network.model.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _searchResult: MutableStateFlow<List<SearchResult>> = MutableStateFlow(emptyList())
    val searchResult: StateFlow<List<SearchResult>> = _searchResult

    fun search(searchQuery: String) {
        runCatching {
            viewModelScope.launch {
                searchRepository.searchShow(searchQuery).collectLatest {
                    _searchResult.value = it
                }
            }
        }
    }
}