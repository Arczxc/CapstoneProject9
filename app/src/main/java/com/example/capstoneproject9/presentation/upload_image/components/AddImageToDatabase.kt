package com.example.capstoneproject9.presentation.upload_image.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.upload_image.UploadImageViewModel

@Composable
fun AddImageToDatabase(
    viewModel: UploadImageViewModel = hiltViewModel(),
    showSnackBar: (isImageAddedToDatabase: Boolean) -> Unit
) {
    when(val addImageToDatabaseResponse = viewModel.addImageToDatabaseResponse) {
        is Loading -> ProgressBar()
        is Success -> addImageToDatabaseResponse.data?.let { isImageAddedToDatabase ->
            LaunchedEffect(isImageAddedToDatabase) {
                showSnackBar(isImageAddedToDatabase)
            }
        }
        is Failure -> print(addImageToDatabaseResponse.e)
    }
}