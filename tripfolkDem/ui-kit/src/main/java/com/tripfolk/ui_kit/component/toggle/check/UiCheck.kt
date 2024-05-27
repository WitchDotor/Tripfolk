package com.tripfolk.ui_kit.component.toggle.check

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripfolk.ui_kit.theme.UiIcons
import com.tripfolk.ui_kit.theme.UiTheme

@Composable
fun UiCheck(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    val checkedBorder = UiTheme.colors.primary
    val uncheckedBorder = UiTheme.colors.outline
    val borderState by animateColorAsState(
        label = "borderState",
        targetValue = if (checked) checkedBorder else uncheckedBorder,
    )
    val checkedThumb = UiTheme.colors.primary
    val uncheckedThumb = Color.Transparent
    val thumbState by animateColorAsState(
        label = "thumbState",
        targetValue = if (checked) checkedThumb else uncheckedThumb,
    )

    Surface(
        modifier = modifier
            .clip(CircleShape)
            .toggleable(
                value = checked,
                onValueChange = onCheckedChange,
                enabled = enabled,
            ),
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = borderState,
        )
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp)
                .padding(4.dp),
            imageVector = UiIcons.Check,
            contentDescription = null,
            tint = thumbState,
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            var checked1 by remember { mutableStateOf(false) }
            UiCheck(
                checked = checked1,
                onCheckedChange = { checked1 = it },
            )
            var checked2 by remember { mutableStateOf(true) }
            UiCheck(
                checked = checked2,
                onCheckedChange = { checked2 = it },
            )
        }

    }
}