package com.tripfolk.ui_kit.component.toggle.switch

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripfolk.ui_kit.theme.UiTheme

@Composable
fun UiSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    val checkedBackground = UiTheme.colors.primary
    val uncheckedBackground = UiTheme.colors.onSurface.copy(alpha = 0.2f)
    val backgroundState by animateColorAsState(
        label = "backgroundState",
        targetValue = if (checked) checkedBackground else uncheckedBackground,
    )
    val checkedThumb = UiTheme.colors.onPrimary
    val uncheckedThumb = UiTheme.colors.onSurfaceVariant
    val thumbState by animateColorAsState(
        label = "thumbState",
        targetValue = if (checked) checkedThumb else uncheckedThumb,
    )
    val bias by animateFloatAsState(
        label = "alignmentBias",
        targetValue = if (checked) 1f else -1f,
    )

    Surface(
        modifier = modifier
            .width(44.dp)
            .clip(CircleShape)
            .toggleable(
                value = checked,
                onValueChange = onCheckedChange,
                enabled = enabled,
            ),
        color = backgroundState,
        shape = CircleShape,
    ) {
        Column(
            horizontalAlignment = BiasAlignment.Horizontal(bias),
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .padding(2.dp)
                    .background(thumbState, CircleShape),
            )
        }
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
            UiSwitch(
                checked = checked1,
                onCheckedChange = { checked1 = it },
            )
            var checked2 by remember { mutableStateOf(true) }
            UiSwitch(
                checked = checked2,
                onCheckedChange = { checked2 = it },
            )
        }

    }
}