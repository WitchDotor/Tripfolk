package com.tripfolk.ui_kit.component.navigation_bar

import android.content.res.Configuration
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.tripfolk.ui_kit.theme.UiIcons
import com.tripfolk.ui_kit.theme.UiTheme

@Composable
fun UiNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        content = content,
    )
}

@Composable
fun RowScope.UiNavigationBarItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = UiTheme.colors.primary,
            selectedTextColor = UiTheme.colors.primary,
            unselectedIconColor = UiTheme.colors.onBackground,
            unselectedTextColor = UiTheme.colors.onBackground,
            indicatorColor = UiTheme.colors.surface,
        ),
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        UiNavigationBar {
            repeat(5) {
                UiNavigationBarItem(
                    label = "Person",
                    icon = UiIcons.Person,
                    selected = it == 0,
                    onClick = {},
                )
            }
        }
    }
}
