package com.example.capstoneproject9.domain.model

data class PaymentInfo(
    val checkOutUrl: String? = null,
    val items: List<ShoppingCartItem>? = null,
    val orderId: String? = null,
    val paymentStatus: String? = null,
)
