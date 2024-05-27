package com.tripfolk.ui_kit.component.field

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripfolk.ui_kit.theme.UiIcons
import com.tripfolk.ui_kit.theme.UiTheme

@Composable
fun UiTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    hint: String = "",
    error: String = "",
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val background = UiTheme.colors.surface
    val contentColor = UiTheme.colors.onSurface
    val textStyle = UiTheme.typography.bodyPrimary
    val labelColor = UiTheme.colors.onSurfaceVariant
    val hintColor = UiTheme.colors.onSurfaceVariant
    val errorColor = UiTheme.colors.error
    val borderColor = if (error.isNotBlank()) errorColor else UiTheme.colors.outline

    Column(
        modifier = modifier.padding(vertical = 12.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (label.isNotBlank())
            Text(
                text = label,
                style = textStyle,
                color = labelColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            textStyle = textStyle.copy(color = contentColor),
            enabled = enabled,
            cursorBrush = SolidColor(UiTheme.colors.primary),
            visualTransformation = visualTransformation,
        ) { innerTextField ->
            Surface(
                color = background,
                contentColor = contentColor,
                shape = CircleShape,
                border = BorderStroke(
                    width = 1.dp,
                    color = borderColor,
                ),
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    if (leadingIcon != null)
                        Box(modifier = Modifier.size(24.dp)) {
                            leadingIcon()
                        }
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isBlank())
                            Text(
                                text = hint,
                                style = textStyle,
                                color = hintColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        innerTextField()
                    }
                    if (trailingIcon != null)
                        Box(modifier = Modifier.size(24.dp)) {
                            trailingIcon()
                        }
                }
            }
        }
        if (error.isNotBlank())
            Text(
                text = error,
                style = textStyle,
                color = errorColor,
            )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        var value by remember { mutableStateOf("Value") }
        UiTextField(
            value = value,
            onValueChange = { value = it },
            label = "Label",
            hint = "Hint",
            error = "Error",
            leadingIcon = {
                Icon(
                    imageVector = UiIcons.Add,
                    contentDescription = null,
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = UiIcons.Add,
                    contentDescription = null,
                )
            },
        )
    }
}