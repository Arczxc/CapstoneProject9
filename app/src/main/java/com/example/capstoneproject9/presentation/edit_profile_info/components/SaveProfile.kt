package com.example.capstoneproject9.presentation.edit_profile_info.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.core.Utils
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.presentation.edit_profile_info.EditProfileViewModel

@Composable
fun SaveProfile(
    viewModel: EditProfileViewModel = hiltViewModel()
){
    when (val saveProfileResponse = viewModel.saveProfileReponse){
        is Response.Loading -> Unit
        is Response.Success -> Unit
        is Response.Failure -> LaunchedEffect(Unit) {
            print(saveProfileResponse.e)
        }
    }
}