package com.example.capstoneproject9.presentation.products_customize_order

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.capstoneproject9.presentation.products_customize_order.components.ProductCustomizeContent
import com.example.capstoneproject9.presentation.products_customize_order.components.ProductCustomizeTopAppBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductCustomizeScreen(
    navigateBack: () -> Unit
){
    Scaffold(
        topBar = {
            ProductCustomizeTopAppBar(
                navigateBack = navigateBack
            )
        },
        content = {padding->
            ProductCustomizeContent(
                padding = padding
            )
        }
    )
}