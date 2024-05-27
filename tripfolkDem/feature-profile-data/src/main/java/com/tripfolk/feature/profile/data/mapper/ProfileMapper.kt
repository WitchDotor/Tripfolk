package com.tripfolk.feature.profile.data.mapper

import com.tripfolk.feature.profile.domain.model.ProfileModel
import com.tripfolk.feature.profile.domain.model.UpdateProfileModel
import com.tripfolk.firebase.model.FirebaseUser
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

    fun mapProfile(id: String, user: FirebaseUser) = ProfileModel(
        id = id,
        name = user.name ?: "",
        image = user.image ?: "",
        dateOfBirth = user.dateOfBirth?.let { mapDate(it, FirebaseUser.DATE_FORMAT) },
    )

    fun mapUpdate(profile: UpdateProfileModel) = FirebaseUser(
        name = profile.name,
        image = profile.image,
        dateOfBirth = profile.dateOfBirth?.let { mapDate(it, FirebaseUser.DATE_FORMAT) },
    )

    private fun mapDate(date: Date, pattern: String) = SimpleDateFormat(pattern, Locale.getDefault()).format(date)
    private fun mapDate(date: String, pattern: String) = SimpleDateFormat(pattern, Locale.getDefault()).parse(date)
}