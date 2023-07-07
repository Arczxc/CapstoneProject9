package com.example.capstoneproject9.presentation.products_order_payment.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.presentation.products_order_payment.ProductsOrderPaymentViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductsOrderPaymentScreen(
    viewModel: ProductsOrderPaymentViewModel = hiltViewModel(),
    padding: PaddingValues,
    paymentId: String
){
    LaunchedEffect(Unit){
        viewModel.getPaymentDetails(paymentId)
    }

    PaymentDetails{ paymentDetails ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)){
            Column(
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = paymentDetails.paymentStatus!!)

            }
        }
    }

}