package com.tripfolk.feature.localisation.data

import com.tripfolk.common.data.dataStore.AppDataStore
import com.tripfolk.feature.localisation.domain.LanguageCodesModel
import com.tripfolk.feature.localisation.domain.LocalisationRepository
import javax.inject.Inject

class LocalisationRepositoryImpl @Inject constructor(
    private val dataStore: AppDataStore,
    private val mapper: LocalisationMapper
) : LocalisationRepository {
    

    override fun getCurrentLanguage():LanguageCodesModel {
        return mapper.mapDataToDisplayCodes(dataStore.language.getValue())
    }

    override suspend fun saveCurrentLanguage(languageCode: LanguageCodesModel) {
        dataStore.language.editValue(mapper.mapDisplayToDataCodes(languageCode))
    }
}