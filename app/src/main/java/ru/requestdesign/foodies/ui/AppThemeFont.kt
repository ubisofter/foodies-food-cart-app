package ru.requestdesign.foodies.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import ru.requestdesign.foodies.R

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val typography = Typography(defaultFontFamily = FontFamily(Font(R.font.roboto))
    )

    MaterialTheme(
        typography = typography,
        content = content
    )
}