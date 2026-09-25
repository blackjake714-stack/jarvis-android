package com.jarvis.assistant.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val JarvisColorScheme = darkColorScheme(
    primary = JarvisCyan,
    secondary = JarvisBlue,
    background = JarvisBackground,
    surface = JarvisPanel,
    onPrimary = Color.Black,
    onBackground = JarvisTextPrimary,
    onSurface = JarvisTextPrimary,
    error = JarvisAmberAlert
)

@Composable
fun JarvisTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = JarvisColorScheme,
        typography = JarvisTypography,
        content = content
    )
}
