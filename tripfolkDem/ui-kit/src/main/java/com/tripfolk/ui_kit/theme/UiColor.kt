package com.tripfolk.ui_kit.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val Primary = Color(0xFF7AC678)
private val Secondary = Color(0xFFFF7B38)

private val Success = Color(0xFF7AC678)
private val Error = Color(0xFFF41F52)
private val Warning = Color(0xFFFFCD1A)

private val Grayscale10 = Color(0xFFFDFDFD)
private val Grayscale20 = Color(0xFFECF1F6)
private val Grayscale30 = Color(0xFFE3E9ED)
private val Grayscale40 = Color(0xFFD1D8DD)
private val Grayscale50 = Color(0xFFBFC6CC)
private val Grayscale60 = Color(0xFF9CA4AB)
private val Grayscale70 = Color(0xFF78828A)
private val Grayscale80 = Color(0xFF66707A)
private val Grayscale90 = Color(0xFF434E58)
private val Grayscale100 = Color(0xFF171725)


@Stable
data class UiColor(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val success: Color,
    val onSuccess: Color,
    val error: Color,
    val onError: Color,
    val warning: Color,
    val onWarning: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
) {
    val materialColorScheme = ColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primary,
        onPrimaryContainer = onPrimary,
        inversePrimary = onPrimary,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondary,
        onSecondaryContainer = onSecondary,
        tertiary = secondary,
        onTertiary = onSecondary,
        tertiaryContainer = secondary,
        onTertiaryContainer = onSecondary,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surface,
        onSurfaceVariant = onSurfaceVariant,
        surfaceTint = surface,
        inverseSurface = onSurface,
        inverseOnSurface = surface,
        error = error,
        onError = onError,
        errorContainer = error,
        onErrorContainer = onError,
        outline = outline,
        outlineVariant = outline,
        scrim = outline,
    )
}

fun lightColor(
    primary: Color = Primary,
    onPrimary: Color = Grayscale10,
    secondary: Color = Secondary,
    onSecondary: Color = Grayscale10,
    success: Color = Success,
    onSuccess: Color = Grayscale10,
    error: Color = Error,
    onError: Color = Grayscale10,
    warning: Color = Warning,
    onWarning: Color = Grayscale10,
    background: Color = Grayscale10,
    onBackground: Color = Grayscale100,
    surface: Color = Grayscale10,
    onSurface: Color = Grayscale100,
    onSurfaceVariant: Color = Grayscale70,
    outline: Color = Grayscale70,
) = UiColor(
    primary = primary,
    onPrimary = onPrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    success = success,
    onSuccess = onSuccess,
    error = error,
    onError = onError,
    warning = warning,
    onWarning = onWarning,
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    onSurfaceVariant = onSurfaceVariant,
    outline = outline,
)

fun darkColor(
    primary: Color = Primary,
    onPrimary: Color = Grayscale10,
    secondary: Color = Secondary,
    onSecondary: Color = Grayscale10,
    success: Color = Success,
    onSuccess: Color = Grayscale10,
    error: Color = Error,
    onError: Color = Grayscale10,
    warning: Color = Warning,
    onWarning: Color = Grayscale10,
    background: Color = Grayscale100,
    onBackground: Color = Grayscale10,
    surface: Color = Grayscale100,
    onSurface: Color = Grayscale10,
    onSurfaceVariant: Color = Grayscale70,
    outline: Color = Grayscale70,
) = UiColor(
    primary = primary,
    onPrimary = onPrimary,
    secondary = secondary,
    onSecondary = onSecondary,
    success = success,
    onSuccess = onSuccess,
    error = error,
    onError = onError,
    warning = warning,
    onWarning = onWarning,
    background = background,
    onBackground = onBackground,
    surface = surface,
    onSurface = onSurface,
    onSurfaceVariant = onSurfaceVariant,
    outline = outline,
)

val LocalUiColor = staticCompositionLocalOf<UiColor> { error("No UiColor provided") }