package com.example.shalawat.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ── Color Palette (from Figma) ──────────────────────────────────────────

// Primary greens
val DarkForestGreen = Color(0xFF1A2E2A)       // deep backgrounds
val ForestGreen = Color(0xFF243B35)            // card backgrounds
val EmeraldGreen = Color(0xFF2D6A4F)           // primary accent
val MintGreen = Color(0xFF52B788)              // lighter accent / active chips
val LightMint = Color(0xFFB7E4C7)             // subtle highlights
val PaleMint = Color(0xFFD8F3DC)              // very light green tints

// Gold / Yellow
val GoldenYellow = Color(0xFFD4A843)          // save button, special accents
val LightGold = Color(0xFFE8CD75)             // hover/light gold states

// Neutrals
val OffWhite = Color(0xFFF1F5F0)              // light mode background
val LightGray = Color(0xFFE8ECE6)             // light surface variant
val MediumGray = Color(0xFF9CA89A)            // muted text
val DarkGray = Color(0xFF3A4A44)              // dark text on light

// Category tag colors
val CategoryGreenBg = Color(0xFF2D6A4F)       // category badge background
val CategoryGreenText = Color(0xFFB7E4C7)     // category badge text

// ── Font Families ───────────────────────────────────────────────────────

// Serif font for branding ("Shalawat" title on splash)
// Using system serif as fallback; you can replace with a custom font resource
val SerifFontFamily = FontFamily.Serif

// Sans-serif for body text
val SansFontFamily = FontFamily.Default

// ── Color Schemes ───────────────────────────────────────────────────────

private val DarkColorScheme = darkColorScheme(
    primary = MintGreen,                          // #52B788
    onPrimary = DarkForestGreen,                  // #1A2E2A
    primaryContainer = EmeraldGreen,               // #2D6A4F
    onPrimaryContainer = LightMint,                // #B7E4C7
    secondary = Color(0xFF8FBC8F),                 // muted green
    onSecondary = DarkForestGreen,
    secondaryContainer = ForestGreen,              // #243B35
    onSecondaryContainer = LightMint,
    tertiary = GoldenYellow,                       // #D4A843
    onTertiary = DarkForestGreen,
    tertiaryContainer = Color(0xFF5C4A1E),         // dark gold bg
    onTertiaryContainer = LightGold,
    background = DarkForestGreen,                  // #1A2E2A
    onBackground = Color(0xFFE0E8E0),
    surface = ForestGreen,                         // #243B35
    onSurface = Color(0xFFE0E8E0),
    surfaceVariant = Color(0xFF2F4F40),            // slightly lighter forest
    onSurfaceVariant = Color(0xFFC0CCBE),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    outline = Color(0xFF4A6B5A),                   // border color
    outlineVariant = Color(0xFF354D42),
)

private val LightColorScheme = lightColorScheme(
    primary = EmeraldGreen,                        // #2D6A4F
    onPrimary = Color.White,
    primaryContainer = PaleMint,                   // #D8F3DC
    onPrimaryContainer = DarkForestGreen,
    secondary = Color(0xFF4A6B5A),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFDCEFDB),
    onSecondaryContainer = Color(0xFF0D2818),
    tertiary = GoldenYellow,                       // #D4A843
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFFF0C7),
    onTertiaryContainer = Color(0xFF3D3005),
    background = OffWhite,                         // #F1F5F0
    onBackground = DarkGray,
    surface = Color.White,
    onSurface = DarkGray,
    surfaceVariant = LightGray,                    // #E8ECE6
    onSurfaceVariant = Color(0xFF4A5A50),
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    outline = Color(0xFF72867A),
    outlineVariant = Color(0xFFC2CFC6),
)

// ── Typography ──────────────────────────────────────────────────────────

private val ShalawatTypography = Typography(
    // Branding title (Splash "Shalawat")
    displayLarge = TextStyle(
        fontFamily = SerifFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = SerifFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    // Large headings (serif feel)
    headlineLarge = TextStyle(
        fontFamily = SerifFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = SerifFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = SansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    // Titles (sans-serif)
    titleLarge = TextStyle(
        fontFamily = SansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = SansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = SansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    // Body text
    bodyLarge = TextStyle(
        fontFamily = SansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = SansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = SansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    // Labels
    labelLarge = TextStyle(
        fontFamily = SansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = SansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = SansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

// ── Theme Composable ────────────────────────────────────────────────────

@Composable
fun ShalawatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ShalawatTypography,
        content = content
    )
}
