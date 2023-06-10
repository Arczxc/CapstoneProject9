package com.example.capstoneproject9.presentation.products_order_payment.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.PaymentDetails
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.products_order_payment.ProductsOrderPaymentViewModel


@Composable
fun PaymentDetails(
    viewModel: ProductsOrderPaymentViewModel = hiltViewModel(),
    paymentDetailsContent: @Composable (items: PaymentDetails) -> Unit
){
    when (val paymentDetailsResponse = viewModel.paymentDetailsResponse){
        is Loading -> ProgressBar()
        is Success -> paymentDetailsResponse.data?.let {items ->
            paymentDetailsContent(items)
        }
        is Failure -> LaunchedEffect(Unit){
            print(paymentDetailsResponse.e)
        }
    }
}


