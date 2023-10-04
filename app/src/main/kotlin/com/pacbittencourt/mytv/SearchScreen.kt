package com.pacbittencourt.mytv

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    var searchQuery by rememberSaveable { mutableStateOf("") }
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
            keyboardActions = KeyboardActions(onSearch = {}),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewSearch() {
    SearchScreen()
}