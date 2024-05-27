package com.tripfolk.ui_kit.component.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tripfolk.ui_kit.component.appbar.UiAppBar
import com.tripfolk.ui_kit.component.button.block.UiButtonBlock
import com.tripfolk.ui_kit.component.navigation_bar.UiNavigationBar
import com.tripfolk.ui_kit.component.snackbar.UiSnackbarHost

/**
 * Default app screen.
 *
 * @param appBar The app bar to display at the top of the screen. Usually a [UiAppBar].
 * @param navigationBar The navigation bar to display at the bottom of the screen. Usually a [UiNavigationBar].
 * @param content The content to display.
 */
@Composable
fun UiScreen(
    appBar: @Composable () -> Unit = {},
    navigationBar: @Composable () -> Unit = {},
    snackbarHostState: SnackbarHostState? = null,
    content: @Composable BoxScope.() -> Unit,
) {

    Scaffold(
        topBar = appBar,
        bottomBar = navigationBar,
        snackbarHost = { if (snackbarHostState != null) UiSnackbarHost(snackbarHostState) },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            content = content,
        )
    }
}

/**
 * Screen with a scrollable content and buttons at the bottom.
 *
 * If content is not enough to fill the screen, buttons will be at the bottom of the screen, otherwise they will be at the bottom of the content.
 *
 * @param appBar The app bar to display at the top of the screen. Usually a [UiAppBar].
 * @param navigationBar The navigation bar to display at the bottom of the screen. Usually a [UiNavigationBar].
 * @param buttons The buttons to display at the bottom of the screen. Usually a [UiButtonBlock].
 * @param content The content to display.
 */
@Composable
fun UiScreen(
    appBar: @Composable () -> Unit = {},
    navigationBar: @Composable () -> Unit = {},
    buttons: @Composable () -> Unit,
    snackbarHostState: SnackbarHostState? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    UiScreen(
        appBar = appBar,
        navigationBar = navigationBar,
        snackbarHostState = snackbarHostState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            content()
            Spacer(modifier = Modifier.weight(1f))
            buttons()
        }
    }
}