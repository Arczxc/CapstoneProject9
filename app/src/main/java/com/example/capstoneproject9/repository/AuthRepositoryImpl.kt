package com.example.capstoneproject9.data.repository

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import com.example.capstoneproject9.core.AppConstants.SIGN_IN_REQUEST
import com.example.capstoneproject9.core.AppConstants.SIGN_UP_REQUEST
import com.example.capstoneproject9.core.FirebaseConstants.ADDRESS_INFO
import com.example.capstoneproject9.core.FirebaseConstants.CONTACT_NUMBER
import com.example.capstoneproject9.core.FirebaseConstants.CREATED_AT
import com.example.capstoneproject9.core.FirebaseConstants.DISPLAY_NAME
import com.example.capstoneproject9.core.FirebaseConstants.EMAIL
import com.example.capstoneproject9.core.FirebaseConstants.FULL_ADDRESS
import com.example.capstoneproject9.core.FirebaseConstants.PHOTO_URL
import com.example.capstoneproject9.core.FirebaseConstants.USERS
import com.example.capstoneproject9.domain.model.Response.Failure
import com.example.capstoneproject9.domain.model.Response.Success
import com.example.capstoneproject9.domain.repository.AuthRepository
import com.example.capstoneproject9.domain.repository.OneTapSignInResponse
import com.example.capstoneproject9.domain.repository.SignInWithGoogleResponse
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private val db: FirebaseFirestore
) : AuthRepository {
    override val isAuthenticated = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle(): OneTapSignInResponse {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Success(signUpResult)
            } catch (e: Exception) {
                Failure(e)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(
        googleCredential: AuthCredential
    ): SignInWithGoogleResponse {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                createUserInFirestore()
                createAddressInfo()
            }
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    private suspend fun createUserInFirestore() {
        auth.currentUser?.apply {
            val user = toUser()
            db.collection(USERS).document(uid).set(user).await()
        }
    }

    private suspend fun createAddressInfo() {
        auth.currentUser?.apply {
            db.collection(USERS).document(uid).collection(ADDRESS_INFO).document("address").set(mapOf(
                FULL_ADDRESS to null
            )).await()
        }
    }
}

fun FirebaseUser.toUser() = mapOf(
    CREATED_AT to serverTimestamp(),
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString(),
)