package com.example.capstoneproject9.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.capstoneproject9.core.AppConstants.NO_VALUE
import com.example.capstoneproject9.core.FirebaseConstants.PRODUCTS_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.USERS
import com.example.capstoneproject9.domain.model.Response.Failure
import com.example.capstoneproject9.domain.model.Response.Success
import com.example.capstoneproject9.domain.model.ShoppingCartItems
import com.example.capstoneproject9.domain.repository.ProductsOrderRepository
import com.example.capstoneproject9.domain.repository.ShoppingCartItemsResponse

class ProductsOrderRepositoryImpl(
    db: FirebaseFirestore,
    auth: FirebaseAuth
): ProductsOrderRepository {
    private val uid = auth.currentUser?.uid ?: NO_VALUE
    private val productsOrdersRef = db.collection(USERS).document(uid).collection(PRODUCTS_ORDER)

    override suspend fun getOrderShoppingCartItemsFromFirestore(
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
}