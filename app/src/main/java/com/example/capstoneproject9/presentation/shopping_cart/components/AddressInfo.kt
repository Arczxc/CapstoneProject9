package com.example.capstoneproject9.presentation.shopping_cart.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.ProfileInfo
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.shopping_cart.ShoppingCartViewModel


@Composable
fun AddressInfo(
    viewModel: ShoppingCartViewModel = hiltViewModel(),
    addressInfoContent : @Composable (addressInfo: ProfileInfo) -> Unit
){
    when (val addressResponse = viewModel.addressInfoResponse){
        is Loading -> ProgressBar()
        is Success -> addressResponse.data?.let { addressInfo ->
            addressInfoContent(addressInfo)
        }
        is Failure -> LaunchedEffect(Unit){
            print(addressResponse.e)
        }
    }
}