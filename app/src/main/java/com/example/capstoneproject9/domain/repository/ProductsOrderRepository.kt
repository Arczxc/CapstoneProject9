package com.example.capstoneproject9.domain.repository

import com.example.capstoneproject9.domain.model.PaymentDetails
import com.example.capstoneproject9.domain.model.PaymentInfo
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.TrackingDetails

typealias PaymentInfoResponse = Response<PaymentInfo>
typealias TrackingDetailsResponse = Response<TrackingDetails>
typealias PaymentDetailsResponse =  Response<PaymentDetails>
interface ProductsOrderRepository {
    suspend fun getOrderShoppingCartItemsFromFirestore(orderId: String): ShoppingCartItemsResponse

    suspend fun getPaymentInfoFromFirestore(orderId:String) : PaymentInfoResponse


    suspend fun getTrackingDetailsFromFirestore(orderId:String) : TrackingDetailsResponse


    suspend fun getPaymentDetailsFromFirestore(orderId:String) : PaymentDetailsResponse
}