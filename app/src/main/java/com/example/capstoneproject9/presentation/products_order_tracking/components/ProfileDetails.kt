package com.example.capstoneproject9.presentation.products_order_tracking.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.ProfileInfo
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.products_order_tracking.ProductsOrderTrackingViewModel

@Composable
fun ProfileDetails(
    viewModel: ProductsOrderTrackingViewModel = hiltViewModel(),
    profileContents: @Composable (profileDetails: ProfileInfo) -> Unit
){
    when (val profileResponse = viewModel.profileInfoResponse){
        is Loading -> ProgressBar()
        is Success -> profileResponse.data?.let {profileDetails ->
            profileContents(profileDetails)
        }
        is Failure -> LaunchedEffect(Unit){
            print(profileResponse.e)
        }
    }
}