package com.tripfolk.ui_kit.theme

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: UiColor = if (darkTheme) darkColor() else lightColor(),
    typography: UiTypography = typography(),
    content: @Composable () -> Unit,
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalUiColor provides colors,
        LocalUiTypography provides typography,
        LocalOverscrollConfiguration provides null
    ) {
        MaterialTheme(
            content = content,
            colorScheme = colors.materialColorScheme,
            typography = typography.materialTypography,
        )
    }
}

object UiTheme {
    val colors: UiColor
        @Composable
        @ReadOnlyComposable
        get() = LocalUiColor.current

    val typography: UiTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalUiTypography.current
}