package com.tripfolk.feature.darkmode.data

import com.tripfolk.common.data.dataStore.AppDataStore
import com.tripfolk.feature.darkmode.domain.DarkModeModel
import com.tripfolk.feature.darkmode.domain.DarkModeRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DarkModeRepositoryImpl @Inject constructor(
    private val dataStore: AppDataStore,
    private val mapper: DarkModeMapper
) : DarkModeRepository {

    override fun getDarkModeState() =
        dataStore.darkMode.getValueFlow().map { mapper.mapDataToDisplay(it) }


    override suspend fun switchDarkModeState(darkModeState: DarkModeModel) {
        dataStore.darkMode.editValue(mapper.mapDisplayToData(darkModeState))
    }

}