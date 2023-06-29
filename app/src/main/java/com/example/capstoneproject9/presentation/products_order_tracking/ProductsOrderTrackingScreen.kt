package com.example.capstoneproject9.presentation.products_order_tracking

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.capstoneproject9.components.AppTopBar
import com.example.capstoneproject9.presentation.products_order_tracking.components.ProductTrackingTopAppBar
import com.example.capstoneproject9.presentation.products_order_tracking.components.ProductsOrderTrackingContent

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductOrderTrackingScreen(
    trackingId: String,
    navigateBack: () -> Unit
){
    Scaffold(
        topBar = {
            ProductTrackingTopAppBar (
                navigateBack = navigateBack
            )
        },
        content = {padding ->
            ProductsOrderTrackingContent(
                padding = padding,
                trackingId = trackingId
            )
        }
    )
}