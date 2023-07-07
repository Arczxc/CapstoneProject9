package com.example.capstoneproject9.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class CustomizeOrder(
    val id: String? = null,
    @ServerTimestamp
    val dateOfSubmission: Date? = null,
    val total: Int? = null
)
