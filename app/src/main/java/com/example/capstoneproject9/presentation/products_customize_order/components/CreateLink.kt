package com.example.capstoneproject9.presentation.products_customize_order.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.products_customize_order.ProductsCustomizeViewModel

@Composable
fun CreateLink(
    viewModel: ProductsCustomizeViewModel = hiltViewModel()
){
    when(val createLinkResponse = viewModel.createLinkResponse){
        is Loading -> Unit
        is Success -> Unit
        is Failure -> LaunchedEffect(Unit){
            print(createLinkResponse.e)
        }
    }
}