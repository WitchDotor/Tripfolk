package com.tripfolk.feature.localisation.data

import com.tripfolk.common.data.dataStore.models.LanguageCodesData
import com.tripfolk.feature.localisation.domain.LanguageCodesModel
import javax.inject.Inject

class LocalisationMapper @Inject constructor() {

    fun mapDataToDisplayCodes(data: LanguageCodesData) = when (data) {
        LanguageCodesData.EN -> LanguageCodesModel.ENGLISH
        LanguageCodesData.FR -> LanguageCodesModel.FRANCE
        LanguageCodesData.JA -> LanguageCodesModel.JAPANESE
        LanguageCodesData.RU -> LanguageCodesModel.RUSSIAN
        LanguageCodesData.HI-> LanguageCodesModel.HINDI
    }

    fun mapDisplayToDataCodes(display: LanguageCodesModel) = when (display) {
        LanguageCodesModel.ENGLISH -> LanguageCodesData.EN
        LanguageCodesModel.JAPANESE -> LanguageCodesData.JA
        LanguageCodesModel.RUSSIAN -> LanguageCodesData.RU
        LanguageCodesModel.HINDI -> LanguageCodesData.HI
        LanguageCodesModel.FRANCE -> LanguageCodesData.FR
    }
}