package com.example.leadership.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

val DarkThemeColors = darkColors(
    primary = Color(0xFF699BF7),
    background = Color(0xFF151729),
    surface = Color(0xFF1E2237),
    error = Color(0xFFF70040),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White
)

val LightThemeColors = lightColors(
    primary = Color(0xFF699BF7),
    background = Color.White,
    surface = Color(0xFFDFDFDF),
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color(0xFFD00036),
    onError = Color.White
)

@get:ReadOnlyComposable
@get:Composable
val Colors.darkSurface: Color
    get() = if (isLight) Color(0xFFD4D4D6) else Color(0xFF252A40)

@Composable
fun LeadershipAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = Typography,
        content = content
    )
}