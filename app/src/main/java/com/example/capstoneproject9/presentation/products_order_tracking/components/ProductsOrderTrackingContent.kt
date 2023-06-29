package com.example.capstoneproject9.presentation.products_order_tracking.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.presentation.products_order_tracking.ProductsOrderTrackingViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductsOrderTrackingContent(
    viewModel: ProductsOrderTrackingViewModel = hiltViewModel(),
    padding: PaddingValues,
    trackingId: String
){
    LaunchedEffect(Unit){
        viewModel.getTrackingDetails(trackingId)
    }

    TrackingDetails{ trackingDetails->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)){
            Column(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = trackingDetails.trackingStatus.toString())
            }
        }
    }
    /*Box(modifier = Modifier
        .fillMaxSize()
        .padding()){
        Column {
            Text(text = "hello")
        }
    }*/
}