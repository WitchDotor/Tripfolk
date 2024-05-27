package com.tripfolk.feature.map

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import com.tripfolk.feature.map.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel(),
) {
    val mapId = stringResource(R.string.map_id)
    val accessFineLocation = rememberPermissionState(ACCESS_FINE_LOCATION, viewModel::enableMyLocation)
    val pointsOfInterest by viewModel.pointsOfInterest.collectAsState(emptyList())

    LaunchedEffect(Unit) { accessFineLocation.launchPermissionRequest() }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = viewModel.cameraPositionState,
        properties = viewModel.properties,
        uiSettings = viewModel.uiSettings,
        googleMapOptionsFactory = { GoogleMapOptions().mapId(mapId) }
    ) {
        pointsOfInterest.forEach {
            Marker(
                lat = it.location.lat,
                lng = it.location.lng,
            )
        }
    }
}

@Composable
fun Marker(
    lat: Double,
    lng: Double,
) {
    Marker(
        state = rememberMarkerState(position = LatLng(lat, lng)),
    )
}