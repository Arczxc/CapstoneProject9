package com.example.capstoneproject9.data.repository

import android.util.Log
import com.example.capstoneproject9.core.AppConstants.TAG
import com.example.capstoneproject9.core.FirebaseConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.DESCENDING
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import com.example.capstoneproject9.core.FirebaseConstants.ADDITION_DATE
import com.example.capstoneproject9.core.FirebaseConstants.ADDRESS
import com.example.capstoneproject9.core.FirebaseConstants.ALL_PRODUCT_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.CHECK_OUT_URL
import com.example.capstoneproject9.core.FirebaseConstants.CREATION_DATE
import com.example.capstoneproject9.core.FirebaseConstants.DATE_OF_SUBMISSION
import com.example.capstoneproject9.core.FirebaseConstants.EMAIL
import com.example.capstoneproject9.core.FirebaseConstants.ID
import com.example.capstoneproject9.core.FirebaseConstants.ITEMS
import com.example.capstoneproject9.core.FirebaseConstants.ORDERS
import com.example.capstoneproject9.core.FirebaseConstants.ORDER_ID
import com.example.capstoneproject9.core.FirebaseConstants.PAYMENT_DETAILS
import com.example.capstoneproject9.core.FirebaseConstants.PAYMENT_STATUS
import com.example.capstoneproject9.core.FirebaseConstants.PRODUCTS_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.REFERENCE
import com.example.capstoneproject9.core.FirebaseConstants.SHOPPING_CART
import com.example.capstoneproject9.core.FirebaseConstants.TOTAL
import com.example.capstoneproject9.core.FirebaseConstants.TRACKING_DETAILS
import com.example.capstoneproject9.core.FirebaseConstants.TRACKING_STATUS
import com.example.capstoneproject9.core.FirebaseConstants.USERS
import com.example.capstoneproject9.core.Utils.Companion.calculateShoppingCartTotal
import com.example.capstoneproject9.domain.model.Data
import com.example.capstoneproject9.domain.model.ProfileInfo
import com.example.capstoneproject9.domain.model.Response.Failure
import com.example.capstoneproject9.domain.model.Response.Success
import com.example.capstoneproject9.domain.model.ShoppingCartItem
import com.example.capstoneproject9.domain.repository.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class ShoppingCartRepositoryImpl(
    firebaseDatabase: FirebaseDatabase,
    private val firebaseFirestore: FirebaseFirestore,
    auth: FirebaseAuth
): ShoppingCartRepository, ShoppingCartActionsImpl(firebaseDatabase, firebaseFirestore, auth) {

    private val usersRef = firebaseFirestore.collection(USERS)
    private val allProductRef = firebaseFirestore.collection(ALL_PRODUCT_ORDER)
    private val productsOrdersRef = usersRef.document(uid).collection(PRODUCTS_ORDER)
    private val ordersRef = usersRef.document(uid).collection(ORDERS)
    private val profileRef = firebaseFirestore.collection(USERS).document(uid).collection(FirebaseConstants.ADDRESS_INFO)


    val userEmail = auth.currentUser!!.email
    val userUID = auth.currentUser!!.uid
    val userName = auth.currentUser!!.displayName

    override fun getShoppingCartItemsFromFirestore() = callbackFlow {
        val queryShoppingCartItemsByAdditionDate = firebaseFirestore.collection(USERS)
            .document(uid).collection(SHOPPING_CART).orderBy(ADDITION_DATE, DESCENDING)
        val snapshotListener = queryShoppingCartItemsByAdditionDate.addSnapshotListener { snapshot, e ->
            val shoppingCartItemsResponse = if (snapshot != null) {
                val shoppingCartItems = snapshot.toObjects(ShoppingCartItem::class.java)
                Success(shoppingCartItems)
            } else {
                Failure(e)
            }
            trySend(shoppingCartItemsResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun incrementQuantity(itemId: String): IncrementQuantityResponse {
        return try {
            incrementShoppingCartQuantityInRealtimeDatabase()
            incrementShoppingCartItemQuantityInFirestore(itemId)
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun decrementQuantity(itemId: String): DecrementQuantityResponse {
        return try {
            decrementShoppingCartQuantityInRealtimeDatabase()
            decrementShoppingCartItemQuantityInFirestore(itemId)
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun addOrderInFirestore(items: ShoppingCartItems, paymongo: Data): AddOrderResponse {         // hardcoded address
        return try {
            val orderId = productsOrdersRef.document(paymongo.data.attributes.reference_number).id
            emptyShoppingCartInFirestore()
            deleteShoppingCartInRealtimeDatabase()
            addOrderInFirestore(orderId, items, paymongo)               // paymongo will return payment information
            addProductsOrderInFirestore(orderId, items)
            addAllOrderInFirestore(orderId)
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    private suspend fun addOrderInFirestore(
        orderId: String,
        items: ShoppingCartItems,
        paymongo: Data,

    ) = productsOrdersRef.document(orderId).set(mapOf(
        CHECK_OUT_URL to paymongo.data.attributes.checkout_url,
        PAYMENT_STATUS to paymongo.data.attributes.status,
        CREATION_DATE to serverTimestamp(),
        ORDER_ID to orderId,
        ITEMS to items
    )).await()


    private suspend fun addTrackingDetailsInFirestore(
        orderId: String,
    ) = productsOrdersRef.document(orderId).collection(TRACKING_DETAILS).document(orderId).set(mapOf(
        TRACKING_STATUS to "confirmedOrder",
        ORDER_ID to orderId,
    )).await()


    private suspend fun addPaymentDetailsInFirestore(
        orderId: String,
        paymongo: Data
    ) = productsOrdersRef.document(orderId).collection(PAYMENT_DETAILS).document(orderId).set(mapOf(
        PAYMENT_STATUS to paymongo.data.attributes.status,
        ORDER_ID to orderId,
    )).await()

   /* private suspend fun addReference(
        orderId: String,
        reference: String
    ) = productsOrdersRef.document(orderId).set(hashMapOf(REFERENCE to reference)).await()*/

    private suspend fun addProductsOrderInFirestore(
        orderId: String,
        items: ShoppingCartItems
    ) = ordersRef.document(orderId).set(mapOf(
        ID to orderId,
        DATE_OF_SUBMISSION to serverTimestamp(),
        TOTAL to calculateShoppingCartTotal(items)
    )).await()

    private suspend fun addAllOrderInFirestore(
        orderId: String
    ) = allProductRef.document(orderId).set(mapOf(
        USERS to userUID,
        EMAIL to userEmail,
        ID to orderId,
        DATE_OF_SUBMISSION to serverTimestamp(),
        "status" to "to pay"                             //will add
    )).await()



    override suspend fun getProfileInfoInFirestore(): ProfileInfoResponse {
        return try {
            val profileInfoRef = profileRef.document("address")
            val items = profileInfoRef.get().await().toObject(ProfileInfo::class.java)
            Success(items)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    private suspend fun emptyShoppingCartInFirestore() {
        shoppingCartRef.get().await().documents.forEach { snapshot ->
            snapshot.reference.delete().await()
        }
    }

    //Find the bug

    private suspend fun deleteShoppingCartInRealtimeDatabase() = uidRef.removeValue().await()

    /*val client = OkHttpClient()
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val dataJsonAdapter = moshi.adapter(Data::class.java)
    private suspend fun createLink() {
        val mediaType = "application/json".toMediaTypeOrNull()
        val body = RequestBody.create(
            mediaType,
            "{\"data\":{\"attributes\":{\"amount\":666666,\"description\":\"checkoutURL\"}}}"
        )
        val request = Request.Builder()
            .url("https://api.paymongo.com/v1/links")
            .post(body)
            .addHeader("accept", "application/json")
            .addHeader("content-type", "application/json")
            .addHeader("authorization", "Basic c2tfbGl2ZV9MWTR6ZmN5QkRYQ29HWXROUVNzdDVmNUw6")
            .build()
        val response = client.newCall(request).execute()

        val data = dataJsonAdapter.fromJson(response.body!!.source())

        val link = data!!.data.attributes.checkout_url.toString()
        //return data!!.data.attributes.checkout_url
    }*/


   /* val client = OkHttpClient()
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val dataJsonAdapter = moshi.adapter(Data::class.java)

    override suspend fun createLink(): CreateLinkResponse {
        return try{
            val mediaType = "application/json".toMediaTypeOrNull()
            val body = RequestBody.create(mediaType, "{\"data\":{\"attributes\":{\"amount\":690000,\"description\":\"checkoutURL\"}}}")
            val request = Request.Builder()
                .url("https://api.paymongo.com/v1/links")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Basic c2tfbGl2ZV9MWTR6ZmN5QkRYQ29HWXROUVNzdDVmNUw6")
                .build()
            val response = client.newCall(request).execute()
            val datas = dataJsonAdapter.fromJson(response.body!!.source())
            Log.d(TAG, "HATDOG")
            Success(datas)
        } catch (e: Exception){
            Failure(e)
        }
    }*/
}