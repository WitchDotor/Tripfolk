package com.tripfolk.feature.profile.data.storage

import com.tripfolk.common.data.dataStore.AppDataStore
import com.tripfolk.feature.profile.domain.model.ProfileModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileLocalStorage @Inject constructor(
    private val dataStore: AppDataStore,
) {
    val profile = MutableStateFlow<ProfileModel?>(null)

    suspend fun clear() {
        dataStore.clearStore()
        profile.value = null
    }
}