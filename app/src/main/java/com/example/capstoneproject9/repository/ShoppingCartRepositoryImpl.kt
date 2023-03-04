package com.example.capstoneproject9.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query.Direction.DESCENDING
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import com.example.capstoneproject9.core.FirebaseConstants.ADDITION_DATE
import com.example.capstoneproject9.core.FirebaseConstants.DATE_OF_SUBMISSION
import com.example.capstoneproject9.core.FirebaseConstants.ID
import com.example.capstoneproject9.core.FirebaseConstants.ITEMS
import com.example.capstoneproject9.core.FirebaseConstants.ORDERS
import com.example.capstoneproject9.core.FirebaseConstants.PRODUCTS_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.SHOPPING_CART
import com.example.capstoneproject9.core.FirebaseConstants.TOTAL
import com.example.capstoneproject9.core.FirebaseConstants.USERS
import com.example.capstoneproject9.core.Utils.Companion.calculateShoppingCartTotal
import com.example.capstoneproject9.domain.model.Response.Failure
import com.example.capstoneproject9.domain.model.Response.Success
import com.example.capstoneproject9.domain.model.ShoppingCartItem
import com.example.capstoneproject9.domain.repository.*

class ShoppingCartRepositoryImpl(
    firebaseDatabase: FirebaseDatabase,
    private val firebaseFirestore: FirebaseFirestore,
    auth: FirebaseAuth
): ShoppingCartRepository, ShoppingCartActionsImpl(firebaseDatabase, firebaseFirestore, auth) {

    private val usersRef = firebaseFirestore.collection(USERS)
    private val productsOrdersRef = usersRef.document(uid).collection(PRODUCTS_ORDER)
    private val ordersRef = usersRef.document(uid).collection(ORDERS)

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

    override suspend fun addOrderInFirestore(items: ShoppingCartItems): AddOrderResponse {
        return try {
            val orderId = productsOrdersRef.document().id
            addOrderInFirestore(orderId, items)
            addProductsOrderInFirestore(orderId, items)
            emptyShoppingCartInFirestore()
            deleteShoppingCartInRealtimeDatabase()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    private suspend fun addOrderInFirestore(
        orderId: String,
        items: ShoppingCartItems
    ) = productsOrdersRef.document(orderId).set(mapOf(ITEMS to items)).await()

    private suspend fun addProductsOrderInFirestore(
        orderId: String,
        items: ShoppingCartItems
    ) = ordersRef.add(mapOf(
        ID to orderId,
        DATE_OF_SUBMISSION to serverTimestamp(),
        TOTAL to calculateShoppingCartTotal(items)
    )).await()

    private suspend fun emptyShoppingCartInFirestore() {
        shoppingCartRef.get().await().documents.forEach { snapshot ->
            snapshot.reference.delete().await()
        }
    }

    private suspend fun deleteShoppingCartInRealtimeDatabase() = uidRef.removeValue().await()
}