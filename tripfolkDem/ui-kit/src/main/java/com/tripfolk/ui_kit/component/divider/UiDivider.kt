package com.tripfolk.ui_kit.component.divider

import android.content.res.Configuration
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripfolk.ui_kit.theme.UiTheme

@Composable
fun UiDivider(
    modifier: Modifier = Modifier,
) {
    Divider(
        modifier = modifier.height(1.dp),
        color = UiTheme.colors.outline,
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        UiDivider()
    }
}