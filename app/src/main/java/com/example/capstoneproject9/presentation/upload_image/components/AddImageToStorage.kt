package com.example.capstoneproject9.presentation.upload_image.components

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.upload_image.UploadImageViewModel

@Composable
fun AddImageToStorage(
    viewModel: UploadImageViewModel = hiltViewModel(),
    addImageToDatabase: (downloadUrl: Uri) -> Unit
) {
    when(val addImageToStorageResponse = viewModel.addImageToStorageResponse) {
        is Loading -> ProgressBar()
        is Success -> addImageToStorageResponse.data?.let { downloadUrl ->
            LaunchedEffect(downloadUrl) {
                addImageToDatabase(downloadUrl)
            }
        }
        is Failure -> print(addImageToStorageResponse.e)
    }
}