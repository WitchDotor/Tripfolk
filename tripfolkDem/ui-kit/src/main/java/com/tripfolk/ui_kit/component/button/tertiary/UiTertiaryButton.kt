package com.tripfolk.ui_kit.component.button.tertiary

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.tripfolk.ui_kit.theme.UiTheme

@Composable
fun UiTertiaryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    color: Color = UiTheme.colors.primary,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = color,
        ),
    ) {
        Text(
            text,
            style = UiTheme.typography.bodyPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        UiTertiaryButton(
            text = "Button",
            onClick = {},
        )
    }
}