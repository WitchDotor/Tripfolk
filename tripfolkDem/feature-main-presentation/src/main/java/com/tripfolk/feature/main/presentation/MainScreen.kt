package com.tripfolk.feature.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.tripfolk.feature.main.presentation.R
import com.tripfolk.feature.map.MapScreen
import com.tripfolk.feature.profile.presentation.profile.ProfileScreen
import com.tripfolk.ui_kit.component.navigation_bar.UiNavigationBar
import com.tripfolk.ui_kit.component.navigation_bar.UiNavigationBarItem
import com.tripfolk.ui_kit.component.screen.UiScreen
import com.tripfolk.ui_kit.theme.UiIcons

@Composable
fun MainScreen(
    navigateToEditProfile: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit,
    navigateToTermsAndConditions: () -> Unit,
    navigateToEnter: () -> Unit,
    navigateToLocalisation: () -> Unit
) {
    var currentPage by rememberSaveable { mutableStateOf(MainPages.Home) }
    UiScreen(
        navigationBar = {
            UiNavigationBar {
                for (page in MainPages.entries) {
                    UiNavigationBarItem(
                        label = when (page) {
                            MainPages.Home -> stringResource(R.string.main_screen_home)
                            MainPages.Map -> stringResource(R.string.main_screen_map)
                            MainPages.Profile -> stringResource(R.string.main_screen_profile)
                        },
                        icon = when (page) {
                            MainPages.Home -> UiIcons.Home
                            MainPages.Map -> UiIcons.Map
                            MainPages.Profile -> UiIcons.Person
                        },
                        selected = currentPage == page,
                        onClick = { currentPage = page }
                    )
                }
            }
        }
    ) {
        when (currentPage) {
            MainPages.Home -> Unit
            MainPages.Map -> MapScreen()
            MainPages.Profile -> ProfileScreen(
                navigateToEditProfile = navigateToEditProfile,
                navigateToPrivacyPolicy = navigateToPrivacyPolicy,
                navigateToTermsAndConditions = navigateToTermsAndConditions,
                navigateToEnter = navigateToEnter,
                navigateToLocalisation = navigateToLocalisation
            )
        }
    }
}

private enum class MainPages {
    Home,
    Map,
    Profile,
}