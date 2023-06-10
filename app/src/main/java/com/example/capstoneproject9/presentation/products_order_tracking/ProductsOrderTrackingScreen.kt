package com.example.capstoneproject9.presentation.products_order_tracking

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.capstoneproject9.components.AppTopBar
import com.example.capstoneproject9.presentation.products_order_tracking.components.ProductsOrderTrackingContent

@Composable
@ExperimentalMaterial3Api
fun ProductOrderTrackingScreen(
    trackingId: String,
    navigateToProductSearchScreen: () -> Unit,
    navigateToShoppingCartScreen: () -> Unit,
    navigateBack: () -> Unit
){
    Scaffold(
        topBar = {
            AppTopBar(
                title = trackingId,
                navigateBack = navigateBack,
                onSearchIconClick = navigateToProductSearchScreen,
                onShoppingCartIconClick = navigateToShoppingCartScreen
            )
        },
        content = {padding ->
            ProductsOrderTrackingContent(padding = padding)
        }
    )
}