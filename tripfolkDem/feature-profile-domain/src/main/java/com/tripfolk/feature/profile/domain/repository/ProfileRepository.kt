package com.tripfolk.feature.profile.domain.repository

import com.tripfolk.feature.profile.domain.model.ProfileModel
import com.tripfolk.feature.profile.domain.model.UpdateProfileModel
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfile(): Flow<ProfileModel>
    suspend fun updateProfile(data: UpdateProfileModel)
    suspend fun deleteProfile()
}