package com.tripfolk.feature.darkmode.data

import com.tripfolk.common.data.dataStore.models.DarkModeData
import com.tripfolk.feature.darkmode.domain.DarkModeModel
import javax.inject.Inject

class DarkModeMapper @Inject constructor() {

    fun mapDataToDisplay(dataStore: DarkModeData) = when (dataStore) {
        DarkModeData.DISABLED -> DarkModeModel.DISABLED
        DarkModeData.ENABLED -> DarkModeModel.ENABLED
        DarkModeData.AS_SYSTEM -> DarkModeModel.AS_SYSTEM

    }

    fun mapDisplayToData(display: DarkModeModel) = when (display) {
        DarkModeModel.AS_SYSTEM -> DarkModeData.AS_SYSTEM
        DarkModeModel.DISABLED -> DarkModeData.DISABLED
        DarkModeModel.ENABLED -> DarkModeData.ENABLED
    }
}