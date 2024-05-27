package com.tripfolk.feature.point_of_interest.domain.model

data class PointOfInterestModel(
    val id: String,
    val name: String,
    val image: String,
    val location: LatLngModel,
)

