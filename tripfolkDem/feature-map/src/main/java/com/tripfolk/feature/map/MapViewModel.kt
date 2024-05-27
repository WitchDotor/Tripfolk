package com.tripfolk.feature.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.tripfolk.feature.point_of_interest.domain.repository.PointOfInterestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val pointOfInterestRepository: PointOfInterestRepository,
) : ViewModel() {
    val cameraPositionState = CameraPositionState()
    val uiSettings by mutableStateOf(MapUiSettings())
    var properties by mutableStateOf(MapProperties())

    val pointsOfInterest = pointOfInterestRepository.getPointsOfInterest()

    fun enableMyLocation(enable: Boolean) {
        properties = properties.copy(isMyLocationEnabled = enable)
    }
}