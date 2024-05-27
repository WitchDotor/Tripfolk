package com.tripfolk.ui_kit.component.button.block

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripfolk.ui_kit.component.button.primary.UiPrimaryButton
import com.tripfolk.ui_kit.component.button.secondary.UiSecondaryButton
import com.tripfolk.ui_kit.component.button.tertiary.UiTertiaryButton
import com.tripfolk.ui_kit.theme.UiTheme

/**
 * A block of buttons.
 *
 * Vertical arranged buttons with spacing between them and padding around.
 *
 * Commonly used on page level at the bottom of the page.
 */
@Composable
fun UiButtonBlock(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.padding(vertical = 40.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content,
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        UiButtonBlock {
            UiPrimaryButton(text = "Primary", onClick = { })
            UiSecondaryButton(text = "Secondary", onClick = { })
            UiTertiaryButton(text = "Tertiary", onClick = { })
        }
    }
}