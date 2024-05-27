package com.tripfolk.feature.localisation.domain

interface LocalisationRepository {

    fun getCurrentLanguage(): LanguageCodesModel
    suspend fun saveCurrentLanguage(languageCode: LanguageCodesModel)
}