package com.example.capstoneproject9.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.capstoneproject9.core.AppConstants.NO_VALUE
import com.example.capstoneproject9.core.FirebaseConstants
import com.example.capstoneproject9.core.FirebaseConstants.ADDRESS_INFO
import com.example.capstoneproject9.core.FirebaseConstants.CUSTOMIZE_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.PAYMENT_DETAILS
import com.example.capstoneproject9.core.FirebaseConstants.PRODUCTS_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.TRACKING_DETAILS
import com.example.capstoneproject9.core.FirebaseConstants.USERS
import com.example.capstoneproject9.domain.model.*
import com.example.capstoneproject9.domain.model.Response.Failure
import com.example.capstoneproject9.domain.model.Response.Success
import com.example.capstoneproject9.domain.model.ShoppingCartItems
import com.example.capstoneproject9.domain.repository.*
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject

class ProductsOrderRepositoryImpl(
    db: FirebaseFirestore,
    auth: FirebaseAuth
): ProductsOrderRepository {

    private val uid = auth.currentUser?.uid?: NO_VALUE
    private val productsOrdersRef = db.collection(USERS).document(uid).collection(PRODUCTS_ORDER)
    private val customizeRef = db.collection(USERS).document(uid).collection(CUSTOMIZE_ORDER)
    private val trackingRef = db.collection(USERS).document(uid).collection(TRACKING_DETAILS)
    private val profileRef = db.collection(USERS).document(uid).collection(ADDRESS_INFO)


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

    override suspend fun getCustomizeOrder(): CustomizeOrderResponses {
        return try{
            val customizeRef = customizeRef.document("myCustomizeOrder")
            val items = customizeRef.get().await().toObject(CustomizeOrder::class.java)
            Success(items)
        } catch (e: Exception){
            Failure(e)
        }
    }

}