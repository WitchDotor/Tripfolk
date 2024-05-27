package com.tripfolk.ui_kit.component.list_item.list_item

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripfolk.ui_kit.component.divider.UiDivider
import com.tripfolk.ui_kit.theme.UiIcons
import com.tripfolk.ui_kit.theme.UiTheme
import com.tripfolk.ui_kit.util.loremIpsum

@Composable
fun UiListItem(
    modifier: Modifier = Modifier,
    icon: ImageVector?,
    text: String,
    trailing: @Composable () -> Unit = {},
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
    ) {
        Column {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                if (icon != null) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = rememberVectorPainter(icon),
                        contentDescription = null,
                    )
                }
                Text(
                    modifier = Modifier.weight(1f),
                    text = text,
                    style = UiTheme.typography.subtitleSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                trailing()
            }
            UiDivider(
                modifier = Modifier.padding(
                    start = if (icon != null) {
                        56.dp
                    } else {
                        0.dp
                    }
                )
            )
        }
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        UiListItem(
            icon = UiIcons.Add,
            text = loremIpsum(1),
            onClick = {},
        )
    }
}