package com.example.capstoneproject9.presentation.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.core.AppConstants.SNIPPET
import com.example.capstoneproject9.core.AppConstants.ZOOM
import com.example.capstoneproject9.domain.model.Response.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    viewModel: MapViewModel = hiltViewModel()
){
    val cityCenter = LatLng(14.44800816089377, 120.99897623378757)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cityCenter, ZOOM)
    }

    when(val locationsResponse = viewModel.locationsResponse) {
        is Loading -> ProgressBar()
        is Success -> GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLongClick = { latLng ->
                viewModel.addLocation(latLng.latitude, latLng.longitude)
            },
            content = {
                locationsResponse.data?.forEach { location ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(location.lat!!, location.lng!!)
                        ),
                        title = "${location.lat}, ${location.lng}",
                        snippet = SNIPPET,
                        onInfoWindowLongClick = { marker ->
                            marker.hideInfoWindow()
                            viewModel.deleteLocation(location.id!!)
                        }
                    )
                }
            }
        )
        is Failure -> print(locationsResponse.e)
    }
}