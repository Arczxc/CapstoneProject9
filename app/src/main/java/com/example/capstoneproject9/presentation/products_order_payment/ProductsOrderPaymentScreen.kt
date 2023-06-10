package com.example.capstoneproject9.presentation.products_order_payment

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.capstoneproject9.components.AppTopBar
import com.example.capstoneproject9.presentation.products_order_payment.components.ProductsOrderPaymentScreen

@Composable
@ExperimentalMaterial3Api
fun ProductOrderPaymentScreen(
    paymentId: String,
    navigateToProductSearchScreen: () -> Unit,
    navigateToShoppingCartScreen: () -> Unit,
    navigateBack: () -> Unit
){
    Scaffold(
        topBar = {
            AppTopBar(
                title = paymentId,
                navigateBack = navigateBack,
                onSearchIconClick = navigateToProductSearchScreen,
                onShoppingCartIconClick = navigateToShoppingCartScreen
            )
        },
        content = {padding->
            ProductsOrderPaymentScreen(padding = padding)
        }
    )
}