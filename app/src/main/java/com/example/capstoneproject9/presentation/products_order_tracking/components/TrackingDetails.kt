package com.example.capstoneproject9.presentation.products_order_tracking.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.model.TrackingDetails
import com.example.capstoneproject9.domain.repository.TrackingDetailsResponse
import com.example.capstoneproject9.presentation.products_order.ProductsOrderViewModel
import com.example.capstoneproject9.presentation.products_order_tracking.ProductsOrderTrackingViewModel

@Composable
fun TrackingDetails(
    viewModel: ProductsOrderTrackingViewModel = hiltViewModel(),
    trackingDetailsContent : @Composable (trackingDetails: TrackingDetails) -> Unit,
) {
    when (val trackingOrderResponse = viewModel.trackingDetailsResponse){
        is Loading -> ProgressBar()
        is Success -> trackingOrderResponse.data?.let { trackingDetails ->
            trackingDetailsContent(trackingDetails)
        }
        is Failure -> LaunchedEffect(Unit){
            print(trackingOrderResponse.e)
        }
    }
}
