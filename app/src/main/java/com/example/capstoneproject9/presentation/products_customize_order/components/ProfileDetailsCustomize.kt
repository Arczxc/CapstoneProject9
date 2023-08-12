package com.example.capstoneproject9.presentation.products_customize_order.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.ProfileInfo
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.presentation.products_customize_order.ProductsCustomizeViewModel


@Composable
fun ProfileDetailsCustomize(
    viewModel: ProductsCustomizeViewModel = hiltViewModel(),
    profileContents: @Composable (profileDetails: ProfileInfo) -> Unit
){
    when (val profileResponse = viewModel.profileInfoResponse){
        is Response.Loading -> ProgressBar()
        is Response.Success -> profileResponse.data?.let { profileDetails ->
            profileContents(profileDetails)
        }
        is Response.Failure -> LaunchedEffect(Unit){
            print(profileResponse.e)
        }
    }
}