package com.example.capstoneproject9.presentation.products_order_payment

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.repository.PaymentDetailsResponse
import com.example.capstoneproject9.domain.repository.ProductsOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsOrderPaymentViewModel @Inject constructor(
    private val repo: ProductsOrderRepository
): ViewModel() {
    var paymentDetailsResponse by mutableStateOf<PaymentDetailsResponse>(Loading)
        private set


    fun getPaymentDetails(orderId: String) = viewModelScope.launch{
        paymentDetailsResponse = repo.getPaymentDetailsFromFirestore(orderId)
    }
}