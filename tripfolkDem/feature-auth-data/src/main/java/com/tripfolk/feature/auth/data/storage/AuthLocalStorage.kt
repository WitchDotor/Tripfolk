package com.tripfolk.feature.auth.data.storage

import com.tripfolk.common.data.dataStore.AppDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLocalStorage @Inject constructor(
    private val dataStore: AppDataStore,
) {
    fun getProfileStatus(): Flow<String> = dataStore.profileStatus.getValueFlow()
    suspend fun updateProfileStatus(status: String) = dataStore.profileStatus.editValue(status)
}