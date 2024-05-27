package com.tripfolk.ui_kit.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection

@Composable
operator fun PaddingValues.plus(other: PaddingValues): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    val start = calculateStartPadding(layoutDirection) + other.calculateStartPadding(layoutDirection)
    val top = calculateTopPadding() + other.calculateTopPadding()
    val end = calculateEndPadding(layoutDirection) + other.calculateEndPadding(layoutDirection)
    val bottom = calculateBottomPadding() + other.calculateBottomPadding()
    return PaddingValues(start = start, top = top, end = end, bottom = bottom)
}