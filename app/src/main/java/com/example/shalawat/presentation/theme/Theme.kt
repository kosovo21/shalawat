package com.example.shalawat.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF80CBC4),
    onPrimary = Color(0xFF003733),
    primaryContainer = Color(0xFF00504A),
    onPrimaryContainer = Color(0xFF9EF2E9),
    secondary = Color(0xFFB1CCC8),
    onSecondary = Color(0xFF1C3532),
    secondaryContainer = Color(0xFF334B48),
    onSecondaryContainer = Color(0xFFCDE8E4),
    tertiary = Color(0xFFADCAE6),
    onTertiary = Color(0xFF153349),
    tertiaryContainer = Color(0xFF2D4A61),
    onTertiaryContainer = Color(0xFFCAE6FF),
    background = Color(0xFF0F1514),
    onBackground = Color(0xFFDEE4E2),
    surface = Color(0xFF0F1514),
    onSurface = Color(0xFFDEE4E2),
    surfaceVariant = Color(0xFF3F4947),
    onSurfaceVariant = Color(0xFFBEC9C6),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF006B63),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF9EF2E9),
    onPrimaryContainer = Color(0xFF00201D),
    secondary = Color(0xFF4A6360),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFCDE8E4),
    onSecondaryContainer = Color(0xFF06201D),
    tertiary = Color(0xFF45617A),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFCAE6FF),
    onTertiaryContainer = Color(0xFF001E31),
    background = Color(0xFFF4FBF8),
    onBackground = Color(0xFF161D1C),
    surface = Color(0xFFF4FBF8),
    onSurface = Color(0xFF161D1C),
    surfaceVariant = Color(0xFFDAE5E2),
    onSurfaceVariant = Color(0xFF3F4947),
    error = Color(0xFFBA1A1A),
    onError = Color.White,
)

@Composable
fun ShalawatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
