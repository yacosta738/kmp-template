package io.kmptemplate.ui.base.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun ApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) ApplicationDarkTheme else ApplicationLightTheme

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = ApplicationShapes,
        content = {  Surface(content = content) }
    )
}
