package com.example.capstoneproject9.presentation.products_order_payment

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.capstoneproject9.components.AppTopBar
import com.example.capstoneproject9.presentation.products_order_payment.components.ProductPaymentTopAppBar
import com.example.capstoneproject9.presentation.products_order_payment.components.ProductsOrderPaymentScreen

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductOrderPaymentScreen(
    paymentId: String,
    navigateBack: () -> Unit
){
    Scaffold(
        containerColor = Color.LightGray,
        topBar = {
            ProductPaymentTopAppBar (
                navigateBack = navigateBack
            )
        },
        content = { padding->
            ProductsOrderPaymentScreen(
                padding = padding,
                paymentId = paymentId
            )
        }
    )
}