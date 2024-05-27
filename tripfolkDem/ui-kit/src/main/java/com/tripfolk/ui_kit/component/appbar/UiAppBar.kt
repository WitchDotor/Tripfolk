package com.tripfolk.ui_kit.component.appbar

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tripfolk.ui_kit.R
import com.tripfolk.ui_kit.theme.UiIcons
import com.tripfolk.ui_kit.theme.UiTheme

/**
 * A top app bar.
 *
 * Default top app bar with title, navigation icon and actions.
 *
 * Commonly used on page level at the top of the page.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiAppBar(
    title: String,
    onNavigateUp: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            if (onNavigateUp != null)
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = UiIcons.ArrowBack,
                        contentDescription = stringResource(R.string.app_bar_back_button),
                    )
                }
        },
        actions = actions,
        title = {
            Text(
                text = title,
                style = UiTheme.typography.titlePrimary,
            )
        },
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        UiAppBar(
            title = "Title",
            onNavigateUp = {},
            actions = {
                IconButton(
                    onClick = {},
                ) {
                    Icon(
                        imageVector = UiIcons.Add,
                        contentDescription = null,
                    )
                }
            },
        )
    }
}