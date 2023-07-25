package com.example.capstoneproject9.domain.repository

import com.example.capstoneproject9.domain.model.*


typealias PaymentInfoResponse = Response<PaymentInfo>
typealias TrackingDetailsResponse = Response<TrackingDetails>
typealias PaymentDetailsResponse =  Response<PaymentDetails>
typealias CustomizeOrderResponses = Response<CustomizeOrder>
typealias DeleteCustomizeOrder = Response<Boolean>
typealias DeleteOrderResponse = Response<Boolean>
typealias CreateLinkResponse = Response<Boolean>
typealias RequestRefundResponse = Response<Boolean>
typealias UpdatePaymentResponse = Response<Boolean>
interface ProductsOrderRepository {
    suspend fun getOrderShoppingCartItemsFromFirestore(orderId: String): ShoppingCartItemsResponse

    suspend fun getPaymentInfoFromFirestore(orderId:String) : PaymentInfoResponse


    suspend fun getTrackingDetailsFromFirestore(orderId:String) : TrackingDetailsResponse


    suspend fun getPaymentDetailsFromFirestore(orderId:String) : PaymentDetailsResponse

    suspend fun getProfileInfoInFirestore(): ProfileInfoResponse

    suspend fun getCustomizeOrder(customizeId: String): CustomizeOrderResponses              // will get customize Order

    suspend fun deleteOrder(orderId: String): DeleteOrderResponse

    suspend fun deleteCustomizeOrder(customizeId: String) : DeleteCustomizeOrder           // will delete customize Order

    suspend fun createLink(paymongo: Data): CreateLinkResponse          // paymongo will return payment

    suspend fun requestRefund(orderId:String, reason: String): RequestRefundResponse

    suspend fun updatePayment(orderId:String, paymongo: Data): UpdatePaymentResponse
}