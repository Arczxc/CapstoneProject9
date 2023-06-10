package com.example.capstoneproject9.presentation.products_order.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.PaymentInfo
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.repository.PaymentInfoResponse
import com.example.capstoneproject9.presentation.products_order.ProductsOrderViewModel

@Composable
fun PaymentInfo(
    viewModel: ProductsOrderViewModel = hiltViewModel(),
    paymentInfoContent: @Composable (items: PaymentInfo) -> Unit
){
    when(val paymentInfoResponse = viewModel.paymentInfoResponse) {
        is Loading -> ProgressBar()
        is Success -> paymentInfoResponse.data?.let { items ->
            paymentInfoContent(items)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(paymentInfoResponse.e)
        }
    }
}