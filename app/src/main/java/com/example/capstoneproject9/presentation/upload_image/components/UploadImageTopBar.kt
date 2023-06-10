package com.example.capstoneproject9.presentation.upload_image.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.capstoneproject9.R
import com.example.capstoneproject9.core.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadImageTopAppBar(
    navigateBack: () -> Unit
){

    TopAppBar(
        title = {
            Text(
                text = "ADD 3D",
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(R.color.primary),
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )

}