package com.tripfolk.feature.onboarding.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tripfolk.feature.onboarding.domain.model.OnBoardingModel
import com.tripfolk.ui_kit.component.button.primary.UiPrimaryButton
import com.tripfolk.ui_kit.component.button.tertiary.UiTertiaryButton
import com.tripfolk.ui_kit.component.screen.UiScreen
import com.tripfolk.ui_kit.component.state.UiErrorState
import com.tripfolk.ui_kit.component.state.UiLoadingState
import com.tripfolk.ui_kit.theme.UiTheme
import com.tripfolk.ui_kit.util.rememberNetworkPainter
import kotlinx.coroutines.launch


@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel(),
    navigate: () -> Unit
) {
    viewModel.onBoardingScreenState.let {
        LaunchedEffect(it) {
            if (it == OnBoardingScreenState.OnBoardingOff)
                navigate.invoke()
        }
    }

    when (val state = viewModel.onBoardingScreenState) {
        is OnBoardingScreenState.Content -> ContentLayout(
            navigate = { viewModel.setOnboardingOff() },
            onBoardingModels = state.content
        )

        is OnBoardingScreenState.Error -> ErrorLayout(
            message = state.error.message ?: state.error.toString(),
            onRetryClick = { viewModel.loadContent() }
        )

        else -> LoadingLayout()
    }
}

@Composable
private fun ErrorLayout(
    message: String,
    onRetryClick: () -> Unit,
) {
    UiScreen {
        UiErrorState(
            message = message,
            onRetryClick = onRetryClick,
        )
    }
}


@Composable
private fun LoadingLayout() {
    UiScreen {
        UiLoadingState()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ContentLayout(
    navigate: () -> Unit,
    onBoardingModels: List<OnBoardingModel>
) {

    val coroutineScope = rememberCoroutineScope()

    val pagerState = rememberPagerState(pageCount = {
        onBoardingModels.size
    })

    HorizontalPager(
        modifier = Modifier.fillMaxSize(), state = pagerState
    ) { page ->

        OnBoardingItem(
            title = onBoardingModels[page].title,
            text = onBoardingModels[page].text,
            image = onBoardingModels[page].imageUrl,
            button = {
                if (page + 1 == pagerState.pageCount) GetStartedButton(onClick = navigate)
                else NextButton(onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(page + 1)
                    }
                })
            }
        )
    }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd,
    ) {
        SkipButton(onClick = navigate)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CurrentPageIndicator(pagerState)
        Spacer(modifier = Modifier.size(120.dp))
    }
}

@Composable
private fun NextButton(onClick: () -> Unit) {
    UiPrimaryButton(
        text = stringResource(R.string.onboarding_screen_next_button_text), onClick = onClick
    )
}

@Composable
private fun GetStartedButton(onClick: () -> Unit) {
    UiPrimaryButton(
        text = stringResource(R.string.onboarding_screen_get_started_button_text), onClick = onClick
    )
}

@Composable
private fun SkipButton(onClick: () -> Unit) {
    UiTertiaryButton(
        text = stringResource(R.string.onboarding_screen_skip_button_text),
        color = UiTheme.colors.surface,
        onClick = onClick
    )
}

/**
 * [ColorMatrix]
 */
//https://developer.android.com/jetpack/compose/graphics/images/customize#color-matrix
@Composable
private fun OnBoardingItem(
    title: String,
    text: String, image:
    String,
    button: @Composable () -> Unit
) {
    val brightness = -70f
    val colorMatrix = floatArrayOf(
        1f,
        0f,
        0f,
        0f,
        brightness,
        0f,
        1f,
        0f,
        0f,
        brightness,
        0f,
        0f,
        1f,
        0f,
        brightness,
        0f,
        0f,
        0f,
        1f,
        0f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = rememberNetworkPainter(url = image),
                colorFilter = ColorFilter.colorMatrix(ColorMatrix(colorMatrix)),
                contentScale = ContentScale.Crop
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = UiTheme.typography.headingPrimary,
                color = UiTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = UiTheme.typography.subheadingPrimary,
                color = UiTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.size(56.dp))
            button()
            Spacer(modifier = Modifier.size(40.dp))
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CurrentPageIndicator(pagerState: PagerState) {
    Row(
        horizontalArrangement = Arrangement.Center,
    ) {

        repeat(pagerState.pageCount) { iteration ->
            val indicatorWidth by animateIntAsState(
                targetValue = if (iteration == pagerState.currentPage || iteration == pagerState.targetPage) (24f * ((1.0f + pagerState.currentPageOffsetFraction))).toInt()
                else 8, label = "Changes dot into square"
            )
            val indicatorColor by animateColorAsState(
                targetValue = if (iteration == pagerState.currentPage) UiTheme.colors.primary
                else UiTheme.colors.onPrimary, label = "Changes color from basic to primary"
            )

            IndicatorItem(
                width = indicatorWidth, color = indicatorColor
            )
        }
    }
}

@Composable
private fun IndicatorItem(width: Int, color: Color) {
    Box(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = color)
            .size(height = 8.dp, width = width.dp)
    )
}

@Preview
@Composable
private fun Preview() {
    UiTheme {
        ContentLayout(
            navigate = {}, onBoardingModels = listOf(
                OnBoardingModel(
                    title = "", text = "", imageUrl = ""
                )
            )
        )
    }
}