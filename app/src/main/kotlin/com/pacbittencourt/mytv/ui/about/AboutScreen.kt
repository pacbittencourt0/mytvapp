package com.pacbittencourt.mytv.ui.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
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
        Row {
            val tvMaze = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(id = R.string.app_datasource_label))
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(stringResource(id = R.string.app_datasource_value))
                }
            }
            val uriHandler = LocalUriHandler.current
            val url = stringResource(id = R.string.app_datasource_url)
            ClickableText(text = tvMaze, style = LocalTextStyle.current, onClick = {
                uriHandler.openUri(url)
            })
        }
    }
}