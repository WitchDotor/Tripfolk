package com.tripfolk.feature.profile.data.storage

import com.tripfolk.feature.profile.data.mapper.ProfileMapper
import com.tripfolk.feature.profile.domain.model.ProfileModel
import com.tripfolk.feature.profile.domain.model.UpdateProfileModel
import com.tripfolk.firebase.auth.FirebaseAuth
import com.tripfolk.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRemoteStorage @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val mapper: ProfileMapper,
) {
    suspend fun getProfile(): ProfileModel {
        val response = firestore.getUser(auth.profileId) ?: error("User not found")
        return mapper.mapProfile(auth.profileId, response)
    }

    suspend fun updateProfile(data: UpdateProfileModel): ProfileModel {
        firestore.updateUser(auth.profileId, mapper.mapUpdate(data))
        return getProfile()
    }

    suspend fun deleteProfile() {
        firestore.deleteUser(auth.profileId)
        auth.deleteAccount()
    }
}