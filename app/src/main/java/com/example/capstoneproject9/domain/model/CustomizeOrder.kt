package com.example.capstoneproject9.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class CustomizeOrder(
    val id: String? = null,
    @ServerTimestamp
    val dateOfSubmission: Date? = null,
    val photoUrl: String? = null,
    val total: Int? = null,
    val size: Int? = null,
    val color: String? = null,
)
