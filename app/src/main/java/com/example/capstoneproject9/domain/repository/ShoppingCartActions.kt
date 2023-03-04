package com.example.capstoneproject9.domain.repository

import com.example.capstoneproject9.domain.model.ShoppingCartItem

interface ShoppingCartActions {
    suspend fun incrementShoppingCartQuantityInRealtimeDatabase()

    suspend fun incrementShoppingCartItemQuantityInFirestore(itemId: String)

    suspend fun addShoppingCartItemToFirestore(item: ShoppingCartItem)

    fun decrementShoppingCartQuantityInRealtimeDatabase()

    suspend fun decrementShoppingCartItemQuantityInFirestore(itemId: String)
}