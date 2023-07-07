package com.example.capstoneproject9.presentation.products_order_tracking.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        viewModel.getProfile()
    }
        ProfileDetails { profileDetails ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding)){
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = profileDetails.recipientName.toString())
                    Text(text = profileDetails.fullAddress.toString())
                    Text(text = profileDetails.contactNumber.toString())

                    TrackingDetails{ trackingDetails->
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)){
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ){
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(text = "To Pay")
                                    Text(text = "To Ship")
                                    Text(text = "To Receive")
                                    Text(text = "To Review")
                                }
                                //Text(text = trackingDetails.trackingStatus.toString())

                                var statusSlider by remember {
                                    mutableStateOf(25f)
                                }

                                Slider(
                                    modifier = Modifier.padding(30.dp),
                                    value = statusSlider,
                                    onValueChange = {
                                        statusSlider = it
                                    },
                                    valueRange = 0f..100f
                                )

                            }
                        }
                    }
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