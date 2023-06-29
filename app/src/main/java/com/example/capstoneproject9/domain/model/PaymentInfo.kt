package com.example.capstoneproject9.domain.model

data class PaymentInfo(
    val address: String? = null,
    val checkOutUrl: String? = null,
    val items: List<ShoppingCartItem>? = null,
    val orderId: String? = null,
    val paymentStatus: String? = null,
    val referenceNumber: String? = null
)
