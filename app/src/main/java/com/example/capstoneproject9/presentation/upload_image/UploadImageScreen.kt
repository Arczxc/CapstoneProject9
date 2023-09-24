package com.example.capstoneproject9.presentation.upload_image

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.capstoneproject9.presentation.upload_image.components.UploadImageContent
import com.example.capstoneproject9.presentation.upload_image.components.UploadImageTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadImageScreen(
    navigateBack: () -> Unit,
    navigateToThankYouScreen: () -> Unit,
){

    Scaffold(
        containerColor = Color.LightGray,
        topBar = {
            UploadImageTopAppBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            UploadImageContent(
                padding = padding,
                navigateToThankYouScreen = navigateToThankYouScreen
            )
        }
    )

}