package com.tripfolk.feature.profile.domain.model

import java.util.Date

data class UpdateProfileModel(
    val name: String? = null,
    val image: String? = null,
    val dateOfBirth: Date? = null,
)