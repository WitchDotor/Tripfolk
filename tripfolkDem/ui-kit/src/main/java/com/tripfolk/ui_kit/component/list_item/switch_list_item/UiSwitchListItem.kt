package com.tripfolk.ui_kit.component.list_item.switch_list_item

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.tripfolk.ui_kit.component.list_item.list_item.UiListItem
import com.tripfolk.ui_kit.component.toggle.switch.UiSwitch
import com.tripfolk.ui_kit.theme.UiIcons
import com.tripfolk.ui_kit.theme.UiTheme
import com.tripfolk.ui_kit.util.loremIpsum

@Composable
fun UiSwitchListItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    UiListItem(
        modifier = modifier,
        icon = icon,
        text = text,
        trailing = {
            UiSwitch(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        },
        onClick = { onCheckedChange(!checked) },
    )
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        var checked by remember { mutableStateOf(true) }
        UiSwitchListItem(
            icon = UiIcons.Add,
            text = loremIpsum(1),
            checked = checked,
            onCheckedChange = { checked = it },
        )
    }
}