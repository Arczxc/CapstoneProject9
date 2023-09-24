package com.example.capstoneproject9.presentation.products_order

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.capstoneproject9.components.AppTopBar
import com.example.capstoneproject9.presentation.products_order.components.ProductOrderTopBar
import com.example.capstoneproject9.presentation.products_order.components.ProductsOrderContent

@Composable
@ExperimentalMaterial3Api
fun ProductsOrderScreen(
    orderId: String,
    navigateToProductSearchScreen: () -> Unit,
    navigateToShoppingCartScreen: () -> Unit,
    navigateBack: () -> Unit,
    navigateToPaymentDetailsScreen: (paymentId: String) -> Unit,
    navigateToTrackingDetailsScreen: (trackingId: String) -> Unit,
    navigateToThankYouScreen: () -> Unit
) {
    Scaffold(
        containerColor = Color.LightGray,
        topBar = {
            ProductOrderTopBar(
                navigateBack = navigateBack,
                navigateToThankYouScreen = navigateToThankYouScreen,
                orderId = orderId
            )
        },
        content = { padding ->
            ProductsOrderContent(
                padding = padding,
                orderId = orderId,
                navigateToPaymentDetailsScreen = navigateToPaymentDetailsScreen,
                navigateToTrackingDetailsScreen = navigateToTrackingDetailsScreen,
                navigateToThankYouScreen = navigateToThankYouScreen
            )
        }
    )
}