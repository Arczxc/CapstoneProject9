package com.example.capstoneproject9.domain.repository

import com.example.capstoneproject9.domain.model.*


typealias PaymentInfoResponse = Response<PaymentInfo>
typealias TrackingDetailsResponse = Response<TrackingDetails>
typealias PaymentDetailsResponse =  Response<PaymentDetails>
typealias CustomizeOrderResponses = Response<CustomizeOrder>
interface ProductsOrderRepository {
    suspend fun getOrderShoppingCartItemsFromFirestore(orderId: String): ShoppingCartItemsResponse

    suspend fun getPaymentInfoFromFirestore(orderId:String) : PaymentInfoResponse


    suspend fun getTrackingDetailsFromFirestore(orderId:String) : TrackingDetailsResponse


    suspend fun getPaymentDetailsFromFirestore(orderId:String) : PaymentDetailsResponse

    suspend fun getProfileInfoInFirestore(): ProfileInfoResponse

    suspend fun getCustomizeOrder(): CustomizeOrderResponses
}