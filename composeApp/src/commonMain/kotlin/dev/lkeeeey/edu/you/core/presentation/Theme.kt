package dev.lkeeeey.edu.you.core.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun EduYouTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorProvider provides if (isSystemInDarkTheme()) darkPalette else lightPalette,
        content = content
    )
}

object Theme {
    val colors: EduMeColors
        @Composable
        get() = LocalColorProvider.current
}
