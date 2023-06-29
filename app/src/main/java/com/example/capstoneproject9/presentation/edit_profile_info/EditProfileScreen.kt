package com.example.capstoneproject9.presentation.edit_profile_info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.capstoneproject9.presentation.edit_profile_info.components.EditProfileContent

@Composable
@ExperimentalMaterial3Api
fun EditProfileScreen(){
    Box(modifier = Modifier.fillMaxSize()){
        EditProfileContent()
    }
}