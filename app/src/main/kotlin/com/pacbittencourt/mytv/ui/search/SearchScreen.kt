package com.pacbittencourt.mytv.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val searchResult = viewModel.searchResult.collectAsState().value
    Column(
        modifier = modifier.padding(all = 16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchQuery,
            trailingIcon = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "search")
            },
            onValueChange = {
                searchQuery = it
            },
            label = {
                Text(text = "Search TV Shows")
            },
            maxLines = 1,
            singleLine = true,
            keyboardActions = KeyboardActions(onSearch = {
                viewModel.search(searchQuery)
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
        )
        LazyColumn {
            items(searchResult) {
                Text(text = it.show.name)
            }
        }
    }
}
