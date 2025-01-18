package com.kirab.gamehokapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Custom GameHok Colors
object GamehokTheme {
    val Green = Color(0xFF00B167)
    val DarkBackground = Color(0xFF1A1A1A)
    val TextWhite = Color(0xFFFFFFFF)
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