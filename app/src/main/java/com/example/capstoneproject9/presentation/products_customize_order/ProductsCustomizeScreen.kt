package com.example.capstoneproject9.presentation.products_customize_order

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.capstoneproject9.presentation.products_customize_order.components.ProductCustomizeContent
import com.example.capstoneproject9.presentation.products_customize_order.components.ProductCustomizeTopAppBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductCustomizeScreen(
    customizeId: String,
    navigateBack: () -> Unit,
    navigateToThankYouScreen: () -> Unit,
){
    Scaffold(
        topBar = {
            ProductCustomizeTopAppBar(
                customizeId = customizeId,
                navigateBack = navigateBack,
                navigateToThankYouScreen = navigateToThankYouScreen,
            )
        },
        content = {padding->
            ProductCustomizeContent(
                customizeId = customizeId,
                padding = padding,
                navigateToThankYouScreen = navigateToThankYouScreen
            )
        }
    )
}