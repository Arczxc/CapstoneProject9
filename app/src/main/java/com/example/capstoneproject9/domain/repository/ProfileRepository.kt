package com.example.capstoneproject9.domain.repository

import com.example.capstoneproject9.domain.model.ProfileInfo
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.User

typealias SaveProfileResponse = Response<Boolean>

interface ProfileRepository{

    val user: User

    suspend fun saveProfileInfoInFirestore(
        recipientName: String,
        contactNumber: String,
        houseNumber: String,
        city: String,
        country: String,
        zipCode: String
    ): SaveProfileResponse



}