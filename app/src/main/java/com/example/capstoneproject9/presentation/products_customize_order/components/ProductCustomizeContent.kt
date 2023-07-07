package com.example.capstoneproject9.presentation.products_customize_order.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.presentation.products_customize_order.ProductsCustomizeViewModel

@Composable
fun ProductCustomizeContent(
    viewModel: ProductsCustomizeViewModel = hiltViewModel(),
    padding: PaddingValues
){
    LaunchedEffect(Unit){
        viewModel.getCustomizeOrder()
    }

    CustomizeOrders{customizeOrder ->

    }
}