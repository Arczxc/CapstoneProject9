package com.example.capstoneproject9.presentation.main.components.drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.ProfileInfo
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.main.MainViewModel

@Composable
fun ProfileInfo(
    viewModel: MainViewModel = hiltViewModel(),
    profileContent: @Composable (profileInfo: ProfileInfo) -> Unit
){
    when (val profileResponse = viewModel.profileInfoResponse){
        is Loading -> ProgressBar()
        is Success -> profileResponse.data?.let { profileInfo ->
            profileContent(profileInfo)
        }
        is Failure -> LaunchedEffect(Unit){
            print(profileResponse.e)
        }
    }
}