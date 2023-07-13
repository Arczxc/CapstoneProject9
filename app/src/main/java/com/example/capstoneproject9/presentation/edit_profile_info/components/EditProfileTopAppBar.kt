package com.example.capstoneproject9.presentation.edit_profile_info.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.capstoneproject9.R
import com.example.capstoneproject9.core.AppConstants

@Composable
@ExperimentalMaterial3Api
fun EditProfileTopAppBar(
    navigateBack: () -> Unit
){
    TopAppBar(
        title = {
            Text(
                text = "EDIT MY PROFILE",
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