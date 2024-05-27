package com.tripfolk.ui_kit.component.dialog

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tripfolk.ui_kit.R
import com.tripfolk.ui_kit.component.button.primary.UiPrimaryButton
import com.tripfolk.ui_kit.theme.UiTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiDatePicker(
    yearRange: IntRange = 1900..2100,
    onDismiss: () -> Unit,
    onConfirm: (Date?) -> Unit,
) {
    val state = rememberDatePickerState(yearRange = yearRange)
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            UiPrimaryButton(
                modifier = Modifier.padding(horizontal = 6.dp),
                text = stringResource(R.string.date_picker_confirm_button),
                onClick = { onConfirm(state.selectedDateMillis?.let(::Date)) },
            )
        },
    ) {
        DatePicker(
            state = state,
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        UiDatePicker(
            onDismiss = { },
            onConfirm = {},
        )
    }
}