package com.tripfolk.feature.localisation.presentation

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tripfolk.feature.localisation.domain.LanguageCodesModel
import com.tripfolk.feature.localisation.domain.LocalisationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalisationViewModel @Inject constructor(private val repository: LocalisationRepository) :
    ViewModel() {

    fun setApplicationLocalisation(languageCode: LanguageCodesModel) {
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(
                languageCode.code
            )
        )

        viewModelScope.launch {
            repository.saveCurrentLanguage(languageCode)
        }
    }
}