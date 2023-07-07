package com.example.capstoneproject9.presentation.main.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.repository.CustomizeOrders
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.main.MainViewModel

@Composable
fun CustomizeOrder(
    viewModel: MainViewModel = hiltViewModel(),
    customizeOrderContent: @Composable (customizeOrder: CustomizeOrders) -> Unit
){
    when(val customizeOrderResponse = viewModel.customizeOrderResponse){
        is Loading -> ProgressBar()
        is Success -> customizeOrderResponse.data?.let { customizeOrder ->
            customizeOrderContent(customizeOrder)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(customizeOrderResponse.e)
        }
    }
}