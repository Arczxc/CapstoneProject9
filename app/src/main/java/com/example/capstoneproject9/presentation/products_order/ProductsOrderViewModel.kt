package com.example.capstoneproject9.presentation.products_order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.capstoneproject9.domain.model.Response.Loading
import com.example.capstoneproject9.domain.repository.*
import javax.inject.Inject

@HiltViewModel
class ProductsOrderViewModel @Inject constructor(
    private val repo: ProductsOrderRepository
): ViewModel() {
    var productsOrderResponse by mutableStateOf<ShoppingCartItemsResponse>(Loading)
        private set
    var paymentInfoResponse by mutableStateOf<PaymentInfoResponse>(Loading)
        private set

    fun getOrderShoppingCartItems(orderId: String) = viewModelScope.launch {
        productsOrderResponse = repo.getOrderShoppingCartItemsFromFirestore(orderId)
    }

    fun getPaymentInfo(orderId: String) = viewModelScope.launch {
        paymentInfoResponse = repo.getPaymentInfoFromFirestore(orderId)
    }

}