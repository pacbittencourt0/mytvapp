package com.pacbittencourt.mytv.presentation.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pacbittencourt.mytv.R
import com.pacbittencourt.mytv.data.model.ShowModel
import com.pacbittencourt.mytv.presentation.components.EmptyState
import com.pacbittencourt.mytv.presentation.components.FailedState
import com.pacbittencourt.mytv.presentation.components.LoadingState

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
    val searchResult by viewModel.searchResult.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(4.dp)) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                value = searchQuery,
                trailingIcon = {
                    val icon =
                        if (searchQuery.isEmpty()) Icons.Rounded.Search else Icons.Rounded.Close
                    Icon(
                        modifier = Modifier.clickable { searchQuery = "" },
                        imageVector = icon,
                        contentDescription = "search"
                    )
                },
                onValueChange = {
                    searchQuery = it
                },
                label = {
                    Text(text = stringResource(R.string.search_searchbar_hint))
                },
                maxLines = 1,
                singleLine = true,
                keyboardActions = KeyboardActions(onSearch = {
                    viewModel.search(searchQuery)
                    keyboardController?.hide()
                }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
            )
        }
        when (searchResult) {
            is SearchUiState.Empty -> EmptyState(messages = listOf(stringResource(R.string.search_no_results)))
            is SearchUiState.Idle -> IdleState()
            is SearchUiState.Loading -> LoadingState()
            is SearchUiState.Failed -> FailedState()
            is SearchUiState.Success -> SearchResult(
                searchResult = searchResult
            ) { show -> viewModel.handleShowInWatchList(show) }
        }
    }
}

@Composable
fun IdleState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textStyle = LocalTextStyle.current.copy(
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
        )
        Icon(
            modifier = Modifier.size(96.dp),
            imageVector = Icons.Rounded.Search,
            contentDescription = "search icon",
        )
        Text(text = stringResource(R.string.search_search_your_shows), style = textStyle)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchResult(searchResult: SearchUiState, onAddShowClick: (ShowModel) -> Unit) {
    val data = searchResult as SearchUiState.Success
    LazyColumn {
        items(items = data.searchResults, key = { it.id }) {
            Column(Modifier.animateItemPlacement()) {
                HorizontalDivider()
                SearchResultItem(
                    show = it,
                    onAddShowClick = onAddShowClick
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun SearchResultItem(
    show: ShowModel,
    onAddShowClick: (ShowModel) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .width(72.dp)
                .padding(2.dp),
            model = show.imageMediumUrl,
            placeholder = painterResource(id = R.drawable.baseline_tv_off_24),
            contentDescription = "",
            error = painterResource(id = R.drawable.baseline_tv_off_24),
            contentScale = ContentScale.FillWidth
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            text = show.name
        )
        var isAddedAux by remember { mutableStateOf(show.isAdded) }
        val icon = if (isAddedAux) Icons.Default.Check else Icons.Default.Add
        IconButton(
            modifier = Modifier.padding(horizontal = 8.dp),
            onClick = {
                onAddShowClick(show)
                show.isAdded = !show.isAdded
                isAddedAux = show.isAdded
            }
        ) {
            Icon(imageVector = icon, contentDescription = "add")
        }
    }
}

@Composable
@Preview
private fun PreviewItem() {
    SearchResultItem(
        show = ShowModel(1, "Show", "url", isAdded = false),
    )
}
