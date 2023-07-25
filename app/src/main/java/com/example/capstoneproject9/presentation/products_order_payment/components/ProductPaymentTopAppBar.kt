package com.example.capstoneproject9.presentation.products_order_payment.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.capstoneproject9.R
import com.example.capstoneproject9.components.icons.BackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductPaymentTopAppBar(
    navigateBack: () -> Unit
){
    SmallTopAppBar(
        title = {
            Text(text = "Payment Details")
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(R.color.primary)
        )
    )
}