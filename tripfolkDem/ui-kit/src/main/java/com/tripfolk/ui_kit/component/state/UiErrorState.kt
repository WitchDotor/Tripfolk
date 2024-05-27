package com.tripfolk.ui_kit.component.state

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripfolk.ui_kit.R
import com.tripfolk.ui_kit.component.button.tertiary.UiTertiaryButton
import com.tripfolk.ui_kit.theme.UiTheme
import com.tripfolk.ui_kit.util.loremIpsum

/**
 * Default error state.
 *
 * Usually used as a placeholder when an error occurs while loading data on a screen.
 */
@Composable
fun UiErrorState(
    modifier: Modifier = Modifier,
    message: String,
    onRetryClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
        )
        UiTertiaryButton(
            text = stringResource(R.string.error_state_retry_button),
            onClick = onRetryClick,
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        UiErrorState(
            message = loremIpsum(),
            onRetryClick = {},
        )
    }
}