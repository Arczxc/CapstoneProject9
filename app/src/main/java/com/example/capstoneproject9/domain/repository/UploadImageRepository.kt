package com.example.capstoneproject9.domain.repository

import android.net.Uri
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.User

typealias AddImageToStorageResponse = Response<Uri>
typealias AddImageUrlToFirestoreResponse = Response<Boolean>
typealias GetImageUrlFromFirestoreResponse = Response<String>

interface UploadImageRepository{

    val user: User

    suspend fun addImageToFirebaseStorage(imageUri: Uri): AddImageToStorageResponse

    suspend fun addImageUrlToFirestore(downloadUrl: Uri): AddImageUrlToFirestoreResponse

    suspend fun getImageUrlFromFirestore(): GetImageUrlFromFirestoreResponse
}
