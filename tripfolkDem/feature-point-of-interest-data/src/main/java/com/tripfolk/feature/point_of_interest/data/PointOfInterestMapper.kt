package com.tripfolk.feature.point_of_interest.data

import com.tripfolk.feature.point_of_interest.domain.model.LatLngModel
import com.tripfolk.feature.point_of_interest.domain.model.PointOfInterestModel
import com.tripfolk.firebase.model.FirebaseLatLng
import com.tripfolk.firebase.model.FirebasePointOfInterest
import javax.inject.Inject

class PointOfInterestMapper @Inject constructor() {

    fun mapPointOfInterest(pointOfInterest: FirebasePointOfInterest) = PointOfInterestModel(
        id = pointOfInterest.id ?: "",
        name = pointOfInterest.name ?: "",
        image = pointOfInterest.image ?: "",
        location = pointOfInterest.location?.let(::mapLatLng) ?: error("Location is null"),
    )

    private fun mapLatLng(latLng: FirebaseLatLng) = LatLngModel(
        lat = latLng.lat ?: 0.0,
        lng = latLng.lng ?: 0.0,
    )
}