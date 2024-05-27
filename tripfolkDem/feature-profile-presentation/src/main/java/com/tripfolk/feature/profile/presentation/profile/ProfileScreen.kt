package com.tripfolk.feature.profile.presentation.profile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tripfolk.feature.darkmode.domain.DarkModeModel
import com.tripfolk.feature.profile.presentation.R
import com.tripfolk.ui_kit.component.appbar.UiAppBar
import com.tripfolk.ui_kit.component.button.block.UiButtonBlock
import com.tripfolk.ui_kit.component.button.tertiary.UiTertiaryButton
import com.tripfolk.ui_kit.component.list_item.block.UiListItemBlock
import com.tripfolk.ui_kit.component.list_item.list_item.UiListItem
import com.tripfolk.ui_kit.component.list_item.switch_list_item.UiSwitchListItem
import com.tripfolk.ui_kit.component.screen.UiScreen
import com.tripfolk.ui_kit.component.state.UiErrorState
import com.tripfolk.ui_kit.component.state.UiLoadingState
import com.tripfolk.ui_kit.theme.UiIcons
import com.tripfolk.ui_kit.theme.UiTheme
import com.tripfolk.ui_kit.util.loremIpsum
import com.tripfolk.ui_kit.util.rememberNetworkPainter

@Composable
fun ProfileScreen(
    darkModeViewModel: DarkModeViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navigateToEditProfile: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    navigateToTermsAndConditions: () -> Unit,
    navigateToEnter: () -> Unit,
    navigateToLocalisation: () -> Unit
) {


    val darkMode =
        when (darkModeViewModel.getDarkModeState().collectAsState(initial = null).value) {
            DarkModeModel.ENABLED -> true
            DarkModeModel.DISABLED -> false
            DarkModeModel.AS_SYSTEM -> isSystemInDarkTheme()
            else -> isSystemInDarkTheme()
        }

    profileViewModel.isSignedOut.let { LaunchedEffect(it) { if (it) navigateToEnter() } }

    when (val profileState = profileViewModel.profileState) {
        ProfileState.Loading -> {
            LoadingLayout()
        }

        is ProfileState.Error -> {
            ErrorLayout(
                message = profileState.error.message ?: profileState.error.toString(),
                onRetryClick = profileViewModel::loadProfile,
            )
        }

        is ProfileState.Content -> {
            ContentLayout(
                image = profileState.profile.image,
                name = profileState.profile.name,
                onEditProfileClick = navigateToEditProfile,
                darkMode = darkMode,
                onDarkModeChange = { darkModeViewModel.switchDarkMode(it) },
                onPrivacyPolicyClick = navigateToPrivacyPolicy,
                onTermsAndConditionsClick = navigateToTermsAndConditions,
                onLogoutClick = profileViewModel::signOut,
                onDeleteProfileClick = profileViewModel::deleteProfile,
                onLanguagesClick = navigateToLocalisation
            )
        }
    }
}

@Composable
private fun LoadingLayout() {
    UiScreen(
        appBar = {
            UiAppBar(
                title = stringResource(R.string.profile_screen_title),
            )
        },
    ) {
        UiLoadingState()
    }
}

@Composable
private fun ErrorLayout(
    message: String,
    onRetryClick: () -> Unit,
) {
    UiScreen(
        appBar = {
            UiAppBar(
                title = stringResource(R.string.profile_screen_title),
            )
        },
    ) {
        UiErrorState(
            message = message,
            onRetryClick = onRetryClick,
        )
    }
}

@Composable
private fun ContentLayout(
    image: String,
    name: String,
    onEditProfileClick: () -> Unit,
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onTermsAndConditionsClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onDeleteProfileClick: () -> Unit,
    onLanguagesClick: () -> Unit
) {
    UiScreen(
        appBar = {
            UiAppBar(
                title = stringResource(R.string.profile_screen_title),
            )
        },
        buttons = {
            UiButtonBlock {
                LogoutButton(
                    onClick = onLogoutClick,
                )
                DeleteProfileButton(
                    onClick = onDeleteProfileClick,
                )
            }
        },
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Header(
            image = image,
            name = name,
        )
        PersonalInfoBlock(
            onEditProfileClick = onEditProfileClick,
        )
        GeneralBlock(
            darkMode = darkMode,
            onDarkModeChange = onDarkModeChange,
            onPrivacyPolicyClick = onPrivacyPolicyClick,
            onTermsAndConditionsClick = onTermsAndConditionsClick,
            onLanguagesClick = onLanguagesClick
        )
    }
}

@Composable
private fun Header(
    image: String,
    name: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            painter = rememberNetworkPainter(image, UiIcons.Person),
            contentDescription = null,
        )
        Text(
            text = name,
            style = UiTheme.typography.subtitlePrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun PersonalInfoBlock(
    onEditProfileClick: () -> Unit,
) {
    UiListItemBlock(
        label = stringResource(R.string.profile_screen_personal_block_label),
    ) {
        EditProfileListItem(
            onClick = onEditProfileClick,
        )
    }
}

@Composable
fun GeneralBlock(
    darkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onTermsAndConditionsClick: () -> Unit,
    onLanguagesClick: () -> Unit
) {
    UiListItemBlock(
        label = stringResource(R.string.profile_screen_general_block_label),
    ) {
        DarkModeListItem(
            checked = darkMode,
            onCheckedChange = onDarkModeChange,
        )
        LanguagesListItem(
            onClick = onLanguagesClick
        )
        PrivacyPolicyListItem(
            onClick = onPrivacyPolicyClick,
        )
        TermsAndConditionsListItem(
            onClick = onTermsAndConditionsClick,
        )
    }
}

@Composable
fun EditProfileListItem(
    onClick: () -> Unit,
) {
    UiListItem(
        icon = UiIcons.Person,
        text = stringResource(R.string.profile_screen_edit_profile),
        onClick = onClick,
    )
}

@Composable
fun DarkModeListItem(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    UiSwitchListItem(
        icon = UiIcons.DarkMode,
        text = stringResource(R.string.profile_screen_dark_mode),
        checked = checked,
        onCheckedChange = onCheckedChange,
    )
}

@Composable
fun LanguagesListItem(
    onClick: () -> Unit,
) {
    UiListItem(
        icon = UiIcons.Languages,
        text = stringResource(R.string.profile_screen_languages),
        onClick = onClick,
    )
}


@Composable
fun PrivacyPolicyListItem(
    onClick: () -> Unit,
) {
    UiListItem(
        icon = UiIcons.Policy,
        text = stringResource(R.string.profile_screen_privacy_policy),
        onClick = onClick,
    )
}

@Composable
fun TermsAndConditionsListItem(
    onClick: () -> Unit,
) {
    UiListItem(
        icon = UiIcons.Policy,
        text = stringResource(R.string.profile_screen_terms_and_conditions),
        onClick = onClick,
    )
}

@Composable
private fun LogoutButton(
    onClick: () -> Unit,
) {
    UiTertiaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.profile_screen_logout),
        onClick = onClick,
        color = UiTheme.colors.error,
    )
}

@Composable
private fun DeleteProfileButton(
    onClick: () -> Unit,
) {
    UiTertiaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.profile_screen_delete_profile),
        onClick = onClick,
        color = UiTheme.colors.error,
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LoadingPreview() {
    UiTheme {
        LoadingLayout()
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ErrorPreview() {
    UiTheme {
        ErrorLayout(
            message = loremIpsum(),
            onRetryClick = {},
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ContentPreview() {
    UiTheme {
        ContentLayout(
            image = "",
            name = loremIpsum(1),
            onEditProfileClick = {},
            darkMode = isSystemInDarkTheme(),
            onDarkModeChange = {},
            onPrivacyPolicyClick = {},
            onTermsAndConditionsClick = {},
            onLogoutClick = {},
            onDeleteProfileClick = {},
            onLanguagesClick = {}
        )
    }
}