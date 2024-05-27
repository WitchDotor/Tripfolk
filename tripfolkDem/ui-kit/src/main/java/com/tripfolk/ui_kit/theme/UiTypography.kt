package com.tripfolk.ui_kit.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val HeadingH1Bold = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.Bold)
private val HeadingH1SemiBold = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.SemiBold)
private val HeadingH1Medium = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.Medium)
private val HeadingH2Bold = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)
private val HeadingH2SemiBold = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.SemiBold)
private val HeadingH2Medium = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Medium)
private val HeadingH3Bold = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold)
private val HeadingH3SemiBold = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.SemiBold)
private val HeadingH3Medium = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Medium)
private val HeadingH4Bold = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
private val HeadingH4SemiBold = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
private val HeadingH4Medium = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Medium)
private val HeadingH5Bold = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
private val HeadingH5SemiBold = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
private val HeadingH5Medium = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
private val HeadingH6Bold = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
private val HeadingH6SemiBold = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
private val HeadingH6Medium = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium)

private val BodyExtraLargeBold = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
private val BodyExtraLargeSemiBold = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
private val BodyExtraLargeMedium = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium)
private val BodyLargeBold = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
private val BodyLargeSemiBold = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
private val BodyLargeMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
private val BodyMediumBold = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
private val BodyMediumSemiBold = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
private val BodyMediumMedium = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
private val BodySmallBold = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
private val BodySmallSemiBold = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
private val BodySmallMedium = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium)
private val BodyExtraSmallBold = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold)
private val BodyExtraSmallSemiBold = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
private val BodyExtraSmallMedium = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Medium)

@Stable
data class UiTypography(
    val headingPrimary: TextStyle,
    val headingSecondary: TextStyle,
    val subheadingPrimary: TextStyle,
    val subheadingSecondary: TextStyle,
    val titlePrimary: TextStyle,
    val titleSecondary: TextStyle,
    val subtitlePrimary: TextStyle,
    val subtitleSecondary: TextStyle,
    val bodyPrimary: TextStyle,
    val bodySecondary: TextStyle,
) {
    val materialTypography = Typography(
        displayLarge = headingPrimary,
        displayMedium = headingPrimary,
        displaySmall = headingSecondary,
        headlineLarge = subheadingPrimary,
        headlineMedium = subheadingPrimary,
        headlineSmall = subheadingSecondary,
        titleLarge = titlePrimary,
        titleMedium = titlePrimary,
        titleSmall = titleSecondary,
        bodyLarge = bodyPrimary,
        bodyMedium = bodyPrimary,
        bodySmall = bodySecondary,
        labelLarge = bodySecondary,
        labelMedium = bodySecondary,
        labelSmall = bodySecondary,
    )
}

fun typography(
    headingPrimary: TextStyle = HeadingH4Bold,
    headingSecondary: TextStyle = HeadingH4SemiBold,
    subheadingPrimary: TextStyle = HeadingH6Bold,
    subheadingSecondary: TextStyle = HeadingH6SemiBold,
    titlePrimary: TextStyle = BodyExtraLargeBold,
    titleSecondary: TextStyle = BodyExtraLargeSemiBold,
    subtitlePrimary: TextStyle = BodyLargeBold,
    subtitleSecondary: TextStyle = BodyLargeSemiBold,
    bodyPrimary: TextStyle = BodyMediumMedium,
    bodySecondary: TextStyle = BodySmallMedium,
) = UiTypography(
    headingPrimary = headingPrimary,
    headingSecondary = headingSecondary,
    subheadingPrimary = subheadingPrimary,
    subheadingSecondary = subheadingSecondary,
    titlePrimary = titlePrimary,
    titleSecondary = titleSecondary,
    subtitlePrimary = subtitlePrimary,
    subtitleSecondary = subtitleSecondary,
    bodyPrimary = bodyPrimary,
    bodySecondary = bodySecondary,
)

val LocalUiTypography = staticCompositionLocalOf<UiTypography> { error("No UiTypography provided") }