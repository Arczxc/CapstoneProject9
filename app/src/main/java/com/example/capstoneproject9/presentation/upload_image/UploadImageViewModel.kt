package com.example.capstoneproject9.presentation.upload_image

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.repository.UploadImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UploadImageViewModel @Inject constructor(
    private val repo: UploadImageRepository
): ViewModel() {

    var addImageToStorageResponse by mutableStateOf<Response<Uri>>(Success(null))
        private set
    var addImageToDatabaseResponse by mutableStateOf<Response<Boolean>>(Success(null))
        private set
    var getImageFromDatabaseResponse by mutableStateOf<Response<String>>(Success(null))
        private set

    fun addImageToStorage(imageUri: Uri) = viewModelScope.launch {
        addImageToStorageResponse = Loading
        addImageToStorageResponse = repo.addImageToFirebaseStorage(imageUri)
    }

    fun addImageToDatabase(downloadUrl: Uri) = viewModelScope.launch {
        addImageToDatabaseResponse = Loading
        addImageToDatabaseResponse = repo.addImageUrlToFirestore(downloadUrl)
    }

    fun getImageFromDatabase() = viewModelScope.launch {
        getImageFromDatabaseResponse = Loading
        getImageFromDatabaseResponse = repo.getImageUrlFromFirestore()
    }

}