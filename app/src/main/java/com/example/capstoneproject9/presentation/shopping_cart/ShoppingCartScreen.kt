package com.example.capstoneproject9.presentation.shopping_cart

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.capstoneproject9.presentation.SharedViewModel
import com.example.capstoneproject9.presentation.shopping_cart.components.ShoppingCartContent
import com.example.capstoneproject9.presentation.shopping_cart.components.ShoppingCartTopBar

@Composable
@ExperimentalMaterial3Api
fun ShoppingCartScreen(
    navigateBack: () -> Unit,
    navigateToThankYouScreen: () -> Unit,
    //sharedViewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            ShoppingCartTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            ShoppingCartContent(
                padding = padding,
                navigateToThankYouScreen = navigateToThankYouScreen,
                //sharedViewModel = sharedViewModel
            )
        },
    )
}