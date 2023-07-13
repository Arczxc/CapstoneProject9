package com.example.capstoneproject9.presentation.products_order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    var profileInfoResponse by mutableStateOf<ProfileInfoResponse>(Loading)
        private set
    var trackingDetailsResponse by mutableStateOf<TrackingDetailsResponse>(Loading)
        private set

    var deleteResponse by mutableStateOf<Response<Boolean>>(Success(false))
        private set

    fun getOrderShoppingCartItems(orderId: String) = viewModelScope.launch {
        productsOrderResponse = repo.getOrderShoppingCartItemsFromFirestore(orderId)
    }

    fun getPaymentInfo(orderId: String) = viewModelScope.launch {
        paymentInfoResponse = repo.getPaymentInfoFromFirestore(orderId)
    }

    fun deleteOrder(orderId: String) = viewModelScope.launch {
        deleteResponse = Loading
        deleteResponse = repo.deleteOrder(orderId)
    }

    fun getProfile() = viewModelScope.launch {
        profileInfoResponse = repo.getProfileInfoInFirestore()
    }

    fun getTrackingDetails(trackingId: String) = viewModelScope.launch{
        trackingDetailsResponse = repo.getTrackingDetailsFromFirestore(trackingId)
    }




}