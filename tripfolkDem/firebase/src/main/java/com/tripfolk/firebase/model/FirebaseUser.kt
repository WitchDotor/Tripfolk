package com.tripfolk.firebase.model

data class FirebaseUser(
    val id: String? = null,
    val name: String? = null,
    val image: String? = null,
    val dateOfBirth: String? = null,
    val isGuest: Boolean = false,
) {
    companion object {
        const val DATE_FORMAT = "yyyy-MM-dd"
    }
}