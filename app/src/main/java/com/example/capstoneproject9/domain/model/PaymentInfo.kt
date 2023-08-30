package com.example.capstoneproject9.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class PaymentInfo(
    val checkOutUrl: String? = null,
    val items: List<ShoppingCartItem>? = null,
    val orderId: String? = null,
    val paymentStatus: String? = null,
    val orderStatus: String? = null,
    val trackingStatus: String? = null,
    @ServerTimestamp
    val creationDate: Date? = null,
)    // all data that will display in Product Order
