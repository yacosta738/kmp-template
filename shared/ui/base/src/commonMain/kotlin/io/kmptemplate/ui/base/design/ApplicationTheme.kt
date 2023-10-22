package io.kmptemplate.ui.base.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ApplicationTheme(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    val colors = if (darkTheme) ApplicationDarkTheme else ApplicationLightTheme

    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography,
        shapes = ApplicationShapes,
        content = {
            Surface(
                modifier = modifier,
                content = content,
            )
        },
    )
}
