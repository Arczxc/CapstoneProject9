package com.example.capstoneproject9.presentation.products_order.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.products_order.ProductsOrderViewModel

@Composable
fun DeleteOrder(
    viewModel: ProductsOrderViewModel = hiltViewModel()
){
    when(val deleteResponse = viewModel.deleteResponse){
        is Loading -> Unit
        is Success -> Unit
        is Failure -> LaunchedEffect(Unit){
            print(deleteResponse.e)
        }
    }
}