package com.example.capstoneproject9.presentation.products_order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject9.domain.model.Data
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.capstoneproject9.domain.repository.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
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

    var refundResponse by mutableStateOf<Response<Boolean>>(Success(false))
        private set

    var deleteResponse by mutableStateOf<Response<Boolean>>(Success(false))
        private set

    var updatePaymentResponse by mutableStateOf<Response<Boolean>>(Success(false))
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

    fun getRefundRequest(orderId: String, reason: String) = viewModelScope.launch {
        refundResponse = repo.requestRefund(orderId, reason)
    }


    fun updatePayment(orderId: String, paymongo: Data) = viewModelScope.launch {
        updatePaymentResponse = Loading
        updatePaymentResponse = repo.updatePayment(orderId, paymongo)
    }



    private val client = OkHttpClient()
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val dataJsonAdapter = moshi.adapter(Data::class.java)

    suspend fun updateLink(referenceNumber: String): Data{
        val mediaType = "application/json".toMediaTypeOrNull()
        val request = Request.Builder()
            .url("https://api.paymongo.com/v1/links/$referenceNumber")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("authorization", "Basic c2tfbGl2ZV9MWTR6ZmN5QkRYQ29HWXROUVNzdDVmNUw6")
            .build()

        val response = client.newCall(request).execute()

        val data = dataJsonAdapter.fromJson(response.body!!.source())

        return data!!
    }
}