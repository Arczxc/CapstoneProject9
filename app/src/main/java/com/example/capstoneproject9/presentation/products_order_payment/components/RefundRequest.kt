package com.example.capstoneproject9.presentation.products_order_payment.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.products_order.ProductsOrderViewModel
import com.example.capstoneproject9.presentation.products_order_payment.ProductsOrderPaymentViewModel

@Composable
fun RefundRequest(
    viewModel: ProductsOrderViewModel = hiltViewModel()
){
    when(val refundResponse = viewModel.refundResponse){
        is Loading -> Unit
        is Success -> Unit
        is Failure -> LaunchedEffect(Unit) {
            print(refundResponse.e)
        }
    }
}