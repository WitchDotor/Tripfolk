package com.tripfolk.feature.localisation.presentation

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tripfolk.feature.localisation.domain.LanguageCodesModel
import com.tripfolk.ui_kit.component.appbar.UiAppBar
import com.tripfolk.ui_kit.component.list_item.list_item.UiListItem
import com.tripfolk.ui_kit.component.screen.UiScreen
import com.tripfolk.ui_kit.component.toggle.check.UiCheck
import com.tripfolk.ui_kit.theme.UiTheme

@Composable
fun LocalisationScreen(
    viewModel: LocalisationViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    UiScreen(appBar = {
        UiAppBar(
            title = stringResource(R.string.localisation_screen_appbar_title),
            onNavigateUp = navigateUp
        )
    }) {


        val currentLanguage = AppCompatDelegate.getApplicationLocales().toLanguageTags()

        LazyColumn {
            items(LanguageCodesModel.entries) { languageCodes ->
                LanguageItem(title = stringResource(
                    id = when (languageCodes) {
                        LanguageCodesModel.ENGLISH -> R.string.localisation_screen_english_language_name
                        LanguageCodesModel.FRANCE -> R.string.localisation_screen_france_language_name
                        LanguageCodesModel.HINDI -> R.string.localisation_screen_hindi_language_name
                        LanguageCodesModel.RUSSIAN -> R.string.localisation_screen_russian_language_name
                        LanguageCodesModel.JAPANESE -> R.string.localisation_screen_japanese_language_name
                    }
                ),
                    checked = currentLanguage == languageCodes.code,
                    onClick = {
                        viewModel.setApplicationLocalisation(
                            languageCodes
                        )
                    })
            }
        }
    }
}

@Composable
fun LanguageItem(title: String, checked: Boolean, onClick: () -> Unit) {
    UiListItem(
        icon = null,
        text = title,
        onClick = onClick,
        trailing = {
            if (checked) {
                UiCheck(checked = checked, onCheckedChange = { !checked })
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    UiTheme {
        LocalisationScreen(navigateUp = {})
    }
}