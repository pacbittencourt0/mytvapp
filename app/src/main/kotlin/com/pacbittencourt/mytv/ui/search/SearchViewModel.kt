package com.pacbittencourt.mytv.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pacbittencourt.mytv.network.api.SearchApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val api: SearchApi
) : ViewModel() {

    init {
        viewModelScope.launch {
            api.searchShow()
        }
    }
}