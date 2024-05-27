package com.tripfolk.feature.profile.domain.model

import java.util.Date

data class ProfileModel(
    val id: String,
    val name: String,
    val image: String,
    val dateOfBirth: Date?,
)