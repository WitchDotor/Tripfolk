package com.tripfolk.ui_kit.component.list_item.block

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripfolk.ui_kit.component.list_item.list_item.UiListItem
import com.tripfolk.ui_kit.theme.UiIcons
import com.tripfolk.ui_kit.theme.UiTheme
import com.tripfolk.ui_kit.util.loremIpsum

@Composable
fun UiListItemBlock(
    modifier: Modifier = Modifier,
    label: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp),
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = label,
                style = UiTheme.typography.subtitleSecondary,
                color = UiTheme.colors.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        UiListItemBlock(
            label = loremIpsum(1),
        ) {
            repeat(3) {
                UiListItem(
                    icon = UiIcons.Add,
                    text = loremIpsum(1),
                    onClick = {},
                )
            }
        }
    }
}