package com.pacbittencourt.mytv.presentation.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.pacbittencourt.mytv.BuildConfig
import com.pacbittencourt.mytv.R

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val version = BuildConfig.VERSION_NAME
        Text(
            text = stringResource(id = R.string.app_version, version),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.app_datasource_label, version),
            fontWeight = FontWeight.Bold
        )
    }
}