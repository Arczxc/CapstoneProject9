package com.example.capstoneproject9.domain.model

data class ProfileInfo(
    val recipientName: String? = "Default Value",
    val contactNumber: String? = "fill information",
    val houseNumber: String? = null,
    val city: String? = null,
    val country: String? = null,
    val zipCode: String? = null,
    val fullAddress: String? = "fill information",
)
