package com.example.dogapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFFFF5722),
    secondary = Color(0xFF4CAF50),
    tertiary = Color(0xFF2196F3),
    error = RedError

)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFFFAB91),
    secondary = Color(0xFF81C784),
    tertiary = Color(0xFF64B5F6),
    error = RedError
)

@Composable
fun DogAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}