package com.tripfolk.feature.profile.data

import com.tripfolk.feature.profile.data.storage.ProfileLocalStorage
import com.tripfolk.feature.profile.data.storage.ProfileRemoteStorage
import com.tripfolk.feature.profile.domain.model.ProfileModel
import com.tripfolk.feature.profile.domain.model.UpdateProfileModel
import com.tripfolk.feature.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val remoteStorage: ProfileRemoteStorage,
    private val localStorage: ProfileLocalStorage,
) : ProfileRepository {

    override fun getProfile(): Flow<ProfileModel> {
        return localStorage.profile
            .onStart { localStorage.profile.update { remoteStorage.getProfile() } }
            .filterNotNull()
    }

    override suspend fun updateProfile(data: UpdateProfileModel) {
        val profile = remoteStorage.updateProfile(data)
        localStorage.profile.update { profile }
    }

    override suspend fun deleteProfile() {
        remoteStorage.deleteProfile()
        localStorage.clear()
    }

}