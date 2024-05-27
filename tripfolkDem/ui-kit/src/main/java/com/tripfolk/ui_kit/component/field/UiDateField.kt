package com.tripfolk.ui_kit.component.field

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tripfolk.ui_kit.component.dialog.UiDatePicker
import com.tripfolk.ui_kit.theme.UiIcons
import com.tripfolk.ui_kit.theme.UiTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiDateField(
    modifier: Modifier = Modifier,
    value: Date?,
    onValueChange: (Date) -> Unit,
    yearRange: IntRange,
    label: String = "",
    hint: String = "",
    error: String = "",
    format: String = "dd MMM yyyy",
) {
    val formatter = remember { SimpleDateFormat(format, Locale.getDefault()) }
    var datePickerVisible by remember { mutableStateOf(false) }

    if (datePickerVisible)
        UiDatePicker(
            yearRange = yearRange,
            onDismiss = { datePickerVisible = false },
            onConfirm = {
                datePickerVisible = false
                if (it != null) onValueChange(it)
            },
        )
    UiTextField(
        modifier = modifier,
        value = value?.let(formatter::format) ?: "",
        onValueChange = { if (it.isNotBlank()) onValueChange(formatter.parse(it)) },
        label = label,
        hint = hint,
        error = error,
        enabled = false,
        trailingIcon = {
            IconButton(
                onClick = { datePickerVisible = true },
            ) {
                Icon(
                    imageVector = UiIcons.Calendar,
                    contentDescription = null,
                )
            }
        },
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        var value: Date? by remember { mutableStateOf(null) }
        UiDateField(
            value = value,
            onValueChange = { value = it },
            yearRange = 1900..2021,
            label = "Label",
            hint = "Hint",
            error = "Error",
        )
    }
}