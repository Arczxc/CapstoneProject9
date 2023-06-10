package com.example.capstoneproject9.domain.model

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class Ticket(
    @ServerTimestamp
    val dateOfSubmission: Date? = null,
    val id: String? = null,
    val problem: String? = null,
    val subject: String? = null,
)
