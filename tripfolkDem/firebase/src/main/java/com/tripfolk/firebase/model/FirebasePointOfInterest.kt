package com.tripfolk.firebase.model

data class FirebasePointOfInterest(
    val id: String? = null,
    val name: String? = null,
    val image: String? = null,
    val location: FirebaseLatLng? = null,
)