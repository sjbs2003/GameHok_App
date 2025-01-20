package com.kirab.gamehokapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Custom GameHok Colors
object GamehokTheme {
    val Green = Color(0xFF00B167)
    val PrizeGreen = Color(0xFF1E4434)
    val TournamentGreen = Color(0xFF233B2A)
    val DarkBackground = Color(0xFF08130E)
    val TextWhite = Color(0xFFFFFFFF)

    // Primary gradient for cards and surfaces
    val primaryGradient = Brush.linearGradient(
        colors = listOf(
            GamehokTheme.DarkBackground,
            GamehokTheme.DarkBackground.copy(alpha = 0.8f),
            Color(0xFF1A2C1F)
        )
    )

    // Tournament card gradient
    val tournamentGradient = Brush.linearGradient(
        colors = listOf(
            GamehokTheme.TournamentGreen,
            Color(0xFF2C4A33)
        )
    )

    // Premium card gradient
    val premiumGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFFF8DC),
            Color(0xFFFFE4B5)
        )
    )

    // Prize section gradient
    val prizeGradient = Brush.linearGradient(
        colors = listOf(
            GamehokTheme.PrizeGreen,
            Color(0xFF2C5842)
        )
    )

    // Navigation bar gradient
    val navigationGradient = Brush.verticalGradient(
        colors = listOf(
            GamehokTheme.DarkBackground.copy(alpha = 0.9f),
            GamehokTheme.DarkBackground
        )
    )

    // Top bar gradient
    val topBarGradient = Brush.verticalGradient(
        colors = listOf(
            GamehokTheme.DarkBackground,
            GamehokTheme.DarkBackground.copy(alpha = 0.9f)
        )
    )

    // Background gradient for the entire app
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            GamehokTheme.DarkBackground,
            Color(0xFF0A1710),
            GamehokTheme.DarkBackground
        )
    )
}

// Extension function to create dynamic gradients based on colors
fun createGradient(
    startColor: Color,
    endColor: Color,
    direction: GradientDirection = GradientDirection.Horizontal
): Brush {
    return when (direction) {
        GradientDirection.Horizontal -> Brush.horizontalGradient(
            colors = listOf(startColor, endColor)
        )
        GradientDirection.Vertical -> Brush.verticalGradient(
            colors = listOf(startColor, endColor)
        )
        GradientDirection.Diagonal -> Brush.linearGradient(
            colors = listOf(startColor, endColor),
            start = Offset(0f, 0f),
            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
        )
    }
}

enum class GradientDirection {
    Horizontal,
    Vertical,
    Diagonal
}

private val DarkColorScheme = darkColorScheme(
    primary = GamehokTheme.Green,
    secondary = GamehokTheme.Green,
    tertiary = GamehokTheme.Green,
    background = GamehokTheme.DarkBackground,
    surface = GamehokTheme.DarkBackground,
    onPrimary = GamehokTheme.TextWhite,
    onSecondary = GamehokTheme.TextWhite,
    onTertiary = GamehokTheme.TextWhite,
    onBackground = GamehokTheme.TextWhite,
    onSurface = GamehokTheme.TextWhite
)

private val LightColorScheme = darkColorScheme( // Using dark scheme for consistency
    primary = GamehokTheme.Green,
    secondary = GamehokTheme.Green,
    tertiary = GamehokTheme.Green,
    background = GamehokTheme.DarkBackground,
    surface = GamehokTheme.DarkBackground,
    onPrimary = GamehokTheme.TextWhite,
    onSecondary = GamehokTheme.TextWhite,
    onTertiary = GamehokTheme.TextWhite,
    onBackground = GamehokTheme.TextWhite,
    onSurface = GamehokTheme.TextWhite
)

@Composable
fun GameHokAppTheme(
    darkTheme: Boolean = true, // Always use dark theme by default
    dynamicColor: Boolean = false, // Disable dynamic color by default
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        else -> DarkColorScheme // Always use dark scheme for consistency
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = GamehokTheme.DarkBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}