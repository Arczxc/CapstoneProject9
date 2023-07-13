package com.example.capstoneproject9.repository

import android.net.Uri
import com.example.capstoneproject9.core.AppConstants
import com.example.capstoneproject9.core.FirebaseConstants.ALL_CUSTOMIZE_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.CREATION_DATE
import com.example.capstoneproject9.core.FirebaseConstants.CUSTOMIZE_ORDER
import com.example.capstoneproject9.core.FirebaseConstants.DATE_OF_SUBMISSION
import com.example.capstoneproject9.core.FirebaseConstants.EMAIL
import com.example.capstoneproject9.core.FirebaseConstants.ID
import com.example.capstoneproject9.core.FirebaseConstants.PHOTO_URL
import com.example.capstoneproject9.core.FirebaseConstants.USERS
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.model.User
import com.example.capstoneproject9.domain.repository.AddImageToStorageResponse
import com.example.capstoneproject9.domain.repository.AddImageUrlToFirestoreResponse
import com.example.capstoneproject9.domain.repository.GetImageUrlFromFirestoreResponse
import com.example.capstoneproject9.domain.repository.UploadImageRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


class UploadImageRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val db: FirebaseFirestore,
    auth: FirebaseAuth
): UploadImageRepository {

    override val user = User(
        uid = auth.currentUser?.uid ?: AppConstants.NO_VALUE,
        photoUrl = auth.currentUser?.photoUrl.toString(),
        displayName = auth.currentUser?.displayName ?: AppConstants.NO_VALUE,
        email = auth.currentUser?.email ?: AppConstants.NO_VALUE,
    )

    val uid = auth.currentUser?.uid ?: AppConstants.NO_VALUE
    val userEmail = auth.currentUser!!.email
    val userUID = auth.currentUser!!.uid
    private val usersRef = db.collection(USERS)
    private val allCustomizeOrderRef = db.collection(ALL_CUSTOMIZE_ORDER)
    private val customizeOrderRef = usersRef.document(uid).collection(CUSTOMIZE_ORDER)


    override suspend fun addImageToFirebaseStorage(imageUri: Uri): AddImageToStorageResponse {
        return try {
            val downloadUrl = storage.reference.child(CUSTOMIZE_ORDER).child(uid+".jpeg")
                .putFile(imageUri).await()
                .storage.downloadUrl.await()
            Success(downloadUrl)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun addImageUrlToFirestore(downloadUrl: Uri): AddImageUrlToFirestoreResponse {
        return try {
            customizeOrderRef.document("myCustomizeOrder").set(mapOf(
                ID to uid,
                PHOTO_URL to downloadUrl,
                DATE_OF_SUBMISSION to FieldValue.serverTimestamp()
            )).await()
            addAllCustomizeOrderInFirestore()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }


    private suspend fun addAllCustomizeOrderInFirestore(
    ) = allCustomizeOrderRef.document(uid).set(mapOf(
        EMAIL to userEmail,
        USERS to userUID,
        DATE_OF_SUBMISSION to FieldValue.serverTimestamp(),
    )).await()


    override suspend fun getImageUrlFromFirestore(): GetImageUrlFromFirestoreResponse {
        return try {
            val imageUrl = db.collection(USERS).document(uid).get().await().getString(PHOTO_URL)
            Success(imageUrl)
        } catch (e: Exception) {
            Failure(e)
        }
    }

}