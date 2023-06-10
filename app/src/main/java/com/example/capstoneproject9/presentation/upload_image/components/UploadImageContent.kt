package com.example.capstoneproject9.presentation.upload_image.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.core.FirebaseConstants.ALL_IMAGES
import com.example.capstoneproject9.core.FirebaseConstants.OPEN_GALLERY
import com.example.capstoneproject9.presentation.upload_image.UploadImageViewModel

@ExperimentalMaterial3Api
@Composable
fun UploadImageContent(
    padding: PaddingValues,
    viewModel: UploadImageViewModel = hiltViewModel()
){
    //will add viewmodel instance

    val coroutineScope = rememberCoroutineScope()
    val galleryLauncher =  rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
        imageUri?.let {
            viewModel.addImageToStorage(imageUri)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().padding(padding),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = { galleryLauncher.launch(ALL_IMAGES) }
        ) {
            Text(
                text = OPEN_GALLERY,
                fontSize = 18.sp
            )
        }
    }

    AddImageToStorage(
        addImageToDatabase = { downloadUrl ->
            viewModel.addImageToDatabase(downloadUrl)
        }
    )


}