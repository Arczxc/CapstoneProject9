package com.example.capstoneproject9.presentation.products_customize_order.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.CustomizeOrder
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.products_customize_order.ProductsCustomizeViewModel

@Composable
fun CustomizeOrders(
    viewModel: ProductsCustomizeViewModel = hiltViewModel(),
    customizeOrdersContent: @Composable (customizeOrder: CustomizeOrder) -> Unit
){
  when (val customizeResponse = viewModel.customizeOderResponse){
      is Loading -> ProgressBar()
      is Success -> customizeResponse.data?.let { customizeOrder ->
          customizeOrdersContent(customizeOrder)
      }
      is Failure -> LaunchedEffect(Unit){
          print(customizeResponse.e)
      }
  }
}