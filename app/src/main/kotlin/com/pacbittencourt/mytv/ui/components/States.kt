package com.pacbittencourt.mytv.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.WarningAmber
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.pacbittencourt.mytv.R

@Composable
fun EmptyState(messages: List<String>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textStyle = LocalTextStyle.current.copy(
            fontSize = TextUnit(16f, TextUnitType.Sp),
            fontFamily = FontFamily.SansSerif,
            color = colorResource(id = R.color.blue_700),
            fontWeight = FontWeight.Bold,
        )
        for (message in messages) {
            Text(text = message, style = textStyle)
        }
    }
}

@Composable
fun FailedState() {
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
            imageVector = Icons.Rounded.WarningAmber,
            contentDescription = "warning",
            tint = colorResource(id = R.color.warning_orange)
        )
        Text(text = stringResource(R.string.states_something_wrong), style = textStyle)
        Text(text = stringResource(R.string.states_try_again), style = textStyle)
    }
}


@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}