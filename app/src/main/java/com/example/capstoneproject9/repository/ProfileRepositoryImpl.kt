package com.example.capstoneproject9.repository

import com.example.capstoneproject9.core.AppConstants
import com.example.capstoneproject9.core.FirebaseConstants.ADDRESS_INFO
import com.example.capstoneproject9.core.FirebaseConstants.CITY
import com.example.capstoneproject9.core.FirebaseConstants.CONTACT_NUMBER
import com.example.capstoneproject9.core.FirebaseConstants.COUNTRY
import com.example.capstoneproject9.core.FirebaseConstants.FULL_ADDRESS
import com.example.capstoneproject9.core.FirebaseConstants.HOUSE_NUMBER
import com.example.capstoneproject9.core.FirebaseConstants.RECIPIENT_NAME
import com.example.capstoneproject9.core.FirebaseConstants.USERS
import com.example.capstoneproject9.core.FirebaseConstants.ZIP_CODE
import com.example.capstoneproject9.domain.model.ProfileInfo
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.model.User
import com.example.capstoneproject9.domain.repository.ProfileInfoResponse
import com.example.capstoneproject9.domain.repository.ProfileRepository
import com.example.capstoneproject9.domain.repository.SaveProfileResponse
import com.example.capstoneproject9.presentation.brand_products.capitalizeFirstChar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProfileRepositoryImpl(
    db: FirebaseFirestore,
    auth: FirebaseAuth
): ProfileRepository {

    val uid = auth.currentUser?.uid ?: AppConstants.NO_VALUE
    private val usersRef = db.collection(USERS)
    private val profileRef = usersRef.document(uid).collection(ADDRESS_INFO)


    override val user = User(
        uid = auth.currentUser?.uid ?: AppConstants.NO_VALUE,
        photoUrl = auth.currentUser?.photoUrl.toString(),
        displayName = auth.currentUser?.displayName ?: AppConstants.NO_VALUE,
        email = auth.currentUser?.email ?: AppConstants.NO_VALUE,
    )


    override suspend fun saveProfileInfoInFirestore(
        recipientName: String,
        contactNumber: String,
        houseNumber: String,
        city: String,
        country: String,
        zipCode: String
    ): SaveProfileResponse {
       return try {
           addProfileInFirestore(recipientName, contactNumber, houseNumber, city, country, zipCode)
           Success(true)
       } catch (e: Exception) {
           Failure(e)
       }
    }


    private suspend fun addProfileInFirestore(
        recipientName: String,
        contactNumber: String,
        houseNumber: String,
        city: String,
        country: String,
        zipCode: String
    ) = profileRef.document("address").set(mapOf(
        RECIPIENT_NAME to recipientName.capitalizeFirstChar(),
        CONTACT_NUMBER to contactNumber,
        HOUSE_NUMBER to houseNumber.capitalizeFirstChar(),
        CITY to city.capitalizeFirstChar(),
        COUNTRY to country.capitalizeFirstChar(),
        ZIP_CODE to zipCode,
        FULL_ADDRESS to "${houseNumber.capitalizeFirstChar()} ${city.capitalizeFirstChar()} ${country.capitalizeFirstChar()} $zipCode"
    )).await()



}