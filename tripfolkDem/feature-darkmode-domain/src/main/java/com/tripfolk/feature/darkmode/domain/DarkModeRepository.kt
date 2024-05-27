package com.tripfolk.feature.darkmode.domain

import kotlinx.coroutines.flow.Flow

interface DarkModeRepository {


    suspend fun switchDarkModeState(darkModeState: DarkModeModel)
    fun getDarkModeState(): Flow<DarkModeModel>
}