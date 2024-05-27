package com.tripfolk.feature.profile.presentation.update_profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tripfolk.feature.profile.presentation.R
import com.tripfolk.ui_kit.component.appbar.UiAppBar
import com.tripfolk.ui_kit.component.button.block.UiButtonBlock
import com.tripfolk.ui_kit.component.button.primary.UiPrimaryButton
import com.tripfolk.ui_kit.component.field.UiDateField
import com.tripfolk.ui_kit.component.field.UiTextField
import com.tripfolk.ui_kit.component.screen.UiScreen
import com.tripfolk.ui_kit.component.state.UiLoadingState
import com.tripfolk.ui_kit.theme.UiTheme
import com.tripfolk.ui_kit.util.loremIpsum
import java.util.Date
import java.util.GregorianCalendar

@Composable
fun CreateProfileScreen(
    navigateNext: () -> Unit,
) {
    UpdateProfileScreen(
        title = stringResource(R.string.update_profile_screen_create_title),
        button = stringResource(R.string.update_profile_screen_create_button),
        onNavigateUp = null,
        onUpdated = navigateNext,
    )
}

@Composable
fun EditProfileScreen(
    navigateUp: () -> Unit,
    navigateNext: () -> Unit,
) {
    UpdateProfileScreen(
        title = stringResource(R.string.update_profile_screen_edit_title),
        button = stringResource(R.string.update_profile_screen_edit_button),
        onNavigateUp = navigateUp,
        onUpdated = navigateNext,
    )
}

@Composable
fun UpdateProfileScreen(
    viewModel: UpdateProfileViewModel = hiltViewModel(),
    title: String,
    button: String,
    onNavigateUp: (() -> Unit)?,
    onUpdated: () -> Unit,
) {
    viewModel.updateState.let {
        LaunchedEffect(it) {
            if (it == UpdateState.Completed) {
                viewModel.updateState = UpdateState.Idle
                onUpdated()
            }
        }
    }
    Layout(
        loading = viewModel.loading,
        title = title,
        name = viewModel.name ?: "",
        nameError = when (viewModel.nameError) {
            NameError.Required -> stringResource(R.string.update_profile_screen_name_field_required_error)
            null -> ""
        },
        onNameChange = viewModel::updateName,
        dateOfBirth = viewModel.dateOfBirth,
        dateOfBirthError = when (viewModel.dateOfBirthError) {
            DateOfBirthError.Required -> stringResource(R.string.update_profile_screen_date_of_birth_field_required_error)
            DateOfBirthError.Invalid -> stringResource(R.string.update_profile_screen_date_of_birth_field_invalid_error)
            null -> ""
        },
        onDateOfBirthChange = viewModel::updateDateOfBirth,
        button = button,
        buttonEnabled = viewModel.updateState != UpdateState.Processing,
        onButtonClicked = viewModel::save,
        onNavigateUp = onNavigateUp,
    )
}

@Composable
private fun Layout(
    loading: Boolean,
    title: String,
    name: String,
    nameError: String,
    onNameChange: (String) -> Unit,
    dateOfBirth: Date?,
    dateOfBirthError: String,
    onDateOfBirthChange: (Date?) -> Unit,
    button: String,
    buttonEnabled: Boolean,
    onButtonClicked: () -> Unit,
    onNavigateUp: (() -> Unit)?,
) {
    UiScreen(
        appBar = {
            UiAppBar(
                title = title,
                onNavigateUp = onNavigateUp,
            )
        },
        buttons = {
            if (!loading)
                UiButtonBlock {
                    UiPrimaryButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = button,
                        onClick = onButtonClicked,
                        enabled = buttonEnabled,
                    )
                }
        }
    ) {
        if (loading) {
            Spacer(modifier = Modifier.weight(1f))
            UiLoadingState()
        } else {
            NameField(
                value = name,
                onValueChange = onNameChange,
                error = nameError,
            )
            DateOfBirthField(
                value = dateOfBirth,
                onValueChange = onDateOfBirthChange,
                error = dateOfBirthError,
            )
        }
    }
}

@Composable
private fun NameField(
    value: String,
    onValueChange: (String) -> Unit,
    error: String,
) {
    UiTextField(
        value = value,
        onValueChange = onValueChange,
        label = stringResource(R.string.update_profile_screen_name_field_label),
        hint = stringResource(R.string.update_profile_screen_name_field_hint),
        error = error,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Done,
        ),
    )
}

@Composable
private fun DateOfBirthField(
    value: Date?,
    onValueChange: (Date?) -> Unit,
    error: String,
) {
    UiDateField(
        value = value,
        onValueChange = onValueChange,
        yearRange = 1900..GregorianCalendar.getInstance().get(GregorianCalendar.YEAR),
        label = stringResource(R.string.update_profile_screen_date_of_birth_field_label),
        hint = stringResource(R.string.update_profile_screen_date_of_birth_field_hint),
        error = error,
    )
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    UiTheme {
        Layout(
            loading = false,
            title = loremIpsum(1),
            name = "",
            nameError = "",
            onNameChange = {},
            dateOfBirth = null,
            dateOfBirthError = "",
            onDateOfBirthChange = {},
            button = loremIpsum(1),
            buttonEnabled = true,
            onButtonClicked = {},
            onNavigateUp = null,
        )
    }
}

