package com.example.capstoneproject9.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.capstoneproject9.core.AppConstants.NO_VALUE
import com.example.capstoneproject9.core.FirebaseConstants
import com.example.capstoneproject9.core.FirebaseConstants.ORDERS
import com.example.capstoneproject9.core.FirebaseConstants.ADDRESS_INFO
import com.example.capstoneproject9.core.FirebaseConstants.ALL_CUSTOMIZE_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.ALL_PRODUCT_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.CHECK_OUT_URL
import com.example.capstoneproject9.core.FirebaseConstants.CUSTOMIZE_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.DATE_OF_SUBMISSION
import com.example.capstoneproject9.core.FirebaseConstants.EMAIL
import com.example.capstoneproject9.core.FirebaseConstants.ID
import com.example.capstoneproject9.core.FirebaseConstants.PAYMENT_DETAILS
import com.example.capstoneproject9.core.FirebaseConstants.PAYMENT_STATUS
import com.example.capstoneproject9.core.FirebaseConstants.PRODUCTS_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.REASON
import com.example.capstoneproject9.core.FirebaseConstants.REQUESTED_REFUND
import com.example.capstoneproject9.core.FirebaseConstants.TRACKING_DETAILS
import com.example.capstoneproject9.core.FirebaseConstants.USERS
import com.example.capstoneproject9.domain.model.*
import com.example.capstoneproject9.domain.model.Response.Failure
import com.example.capstoneproject9.domain.model.Response.Success
import com.example.capstoneproject9.domain.model.ShoppingCartItems
import com.example.capstoneproject9.domain.repository.*
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject

class ProductsOrderRepositoryImpl(
    db: FirebaseFirestore,
    auth: FirebaseAuth
): ProductsOrderRepository {

    private val uid = auth.currentUser?.uid?: NO_VALUE
    private val productsOrdersRef = db.collection(USERS).document(uid).collection(PRODUCTS_ORDER)
    private val orderRef = db.collection(USERS).document(uid).collection(ORDERS)
    private val allProductOrderRef = db.collection(ALL_PRODUCT_ORDER)
    private val customizeRef = db.collection(USERS).document(uid).collection(CUSTOMIZE_ORDER)
    private val customizeColelction = db.collection(ALL_CUSTOMIZE_ORDER)
    private val trackingRef = db.collection(USERS).document(uid).collection(TRACKING_DETAILS)
    private val profileRef = db.collection(USERS).document(uid).collection(ADDRESS_INFO)
    private val refundRef = db.collection(REQUESTED_REFUND)

    val userEmail = auth.currentUser!!.email           // Users Email value
    val userUID = auth.currentUser!!.uid               // Users UID value

    override suspend fun getOrderShoppingCartItemsFromFirestore(                                               // will get shopping cart items in an array
        orderId: String
    ): ShoppingCartItemsResponse {
        return try {
            val orderIdRef = productsOrdersRef.document(orderId)
            val items = orderIdRef.get().await().toObject(ShoppingCartItems::class.java)?.items
            Success(items)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getPaymentInfoFromFirestore(orderId: String): PaymentInfoResponse {                      //will get order details
        return try{
            val orderIdRef = productsOrdersRef.document(orderId)
            val items = orderIdRef.get().await().toObject(PaymentInfo::class.java)
            Success(items)
        } catch (e: Exception){
            Failure(e)
        }
    }


    override suspend fun getTrackingDetailsFromFirestore(orderId: String): TrackingDetailsResponse {
        return try{
            val trackingRef = productsOrdersRef.document(orderId).collection(TRACKING_DETAILS).document(orderId)                                                    //trackingRef = productsOrdersRef.document(orderId).collection(TRACKING_DETAILS).document(orderId)
            val items = trackingRef.get().await().toObject(TrackingDetails::class.java)
            Success(items)
        } catch (e: Exception){
            Failure(e)
        }
    }



    override suspend fun getProfileInfoInFirestore(): ProfileInfoResponse {
        return try {
            val profileInfoRef = profileRef.document("address")
            val items = profileInfoRef.get().await().toObject(ProfileInfo::class.java)
            Success(items)
        } catch (e: Exception) {
            Failure(e)
        }
    }


    override suspend fun getPaymentDetailsFromFirestore(orderId: String): PaymentDetailsResponse {
        return try{
            val paymentRef = productsOrdersRef.document(orderId).collection(PAYMENT_DETAILS).document(orderId)
            val items = paymentRef.get().await().toObject(PaymentDetails::class.java)
            Success(items)
        } catch (e: Exception){
            Failure(e)
        }
    }


    override suspend fun getCustomizeOrder(customizeId: String): CustomizeOrderResponses {
        return try{
            val customizeRef = customizeRef.document(customizeId)
            val items = customizeRef.get().await().toObject(CustomizeOrder::class.java)
            Success(items)
        } catch (e: Exception){
            Failure(e)
        }
    }


    override suspend fun deleteOrder(orderId: String): DeleteOrderResponse {
        return try{
            deleteProductsOrder(orderId)
            deleteOrders(orderId)
            deleteTrackingDetails(orderId)
            deletePaymentDetails(orderId)
            deleteAllOrder(orderId)
            Success(true)
        } catch (e: Exception){
            Failure(e)
        }
    }

    private suspend fun deleteProductsOrder(
        orderId: String
    ) = productsOrdersRef.document(orderId).delete()

    private suspend fun deleteTrackingDetails(
        orderId: String
    ) = productsOrdersRef.document(orderId).collection(TRACKING_DETAILS).document(orderId).delete().await()

    private suspend fun deletePaymentDetails(
        orderId: String
    ) = productsOrdersRef.document(orderId).collection(PAYMENT_DETAILS).document(orderId).delete().await()

    private suspend fun deleteOrders(
        orderId: String
    ) = orderRef.document(orderId).delete().await()

    private suspend fun deleteAllOrder(
        orderId: String
    ) = allProductOrderRef.document(orderId).delete().await()

    override suspend fun deleteCustomizeOrder(customizeId: String): DeleteCustomizeOrder {
        return try {
            deleteCustomizeInFirestore(customizeId)
            deleteCustomizeInUser(customizeId)
            Success(true)
        } catch (e: Exception){
            Failure(e)
        }
    }

    private suspend fun deleteCustomizeInFirestore(
        customizeId: String
    ) = customizeColelction.document(customizeId).delete().await()

    private suspend fun deleteCustomizeInUser(
        customizeId: String
    ) = customizeRef.document(customizeId).delete().await()

    override suspend fun createLink(paymongo: Data): CreateLinkResponse {
        return try{
            saveLinkInFirestore(paymongo)
            Success(true)
        } catch (e: Exception){
            Failure(e)
        }
    }


    private suspend fun saveLinkInFirestore(
        paymongo: Data
    ) = customizeRef.document("myCustomizeOrder").update(mapOf(
        CHECK_OUT_URL to paymongo.data.attributes.checkout_url,
        PAYMENT_STATUS to paymongo.data.attributes.status,
    )).await()


    override suspend fun requestRefund(orderId: String, reason: String): RequestRefundResponse {
        return try{
            saveRequestedRefund(orderId, reason)
            Success(true)
        } catch (e: Exception){
            Failure(e)
        }
    }

    override suspend fun updatePayment(orderId: String, paymongo: Data): UpdatePaymentResponse {
        return try {
            updatePaymentInFirestore(orderId, paymongo)
            Success(true)
        } catch (e: Exception){
            Failure(e)
        }
    }

    private suspend fun updatePaymentInFirestore(
        orderId: String,
        paymongo: Data
    ) = productsOrdersRef.document(orderId).update(mapOf(
        PAYMENT_STATUS to paymongo.data.attributes.status,
        "sampleDate" to paymongo.data.attributes.status,
        //SetOptions.merge()
    )).await()


    private suspend fun saveRequestedRefund(
        orderId: String,
        reason: String
    ) = refundRef.document(orderId).set(mapOf(
        USERS to userUID,
        EMAIL to userEmail,
        ID to orderId,
        REASON to reason,
        DATE_OF_SUBMISSION to FieldValue.serverTimestamp()
    )).await()
}