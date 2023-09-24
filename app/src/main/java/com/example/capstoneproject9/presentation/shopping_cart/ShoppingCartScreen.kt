package com.example.capstoneproject9.presentation.shopping_cart

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.capstoneproject9.presentation.shopping_cart.components.ShoppingCartContent
import com.example.capstoneproject9.presentation.shopping_cart.components.ShoppingCartTopBar

@Composable
@ExperimentalMaterial3Api
fun ShoppingCartScreen(
    navigateBackToMainScreen: () -> Unit,
    navigateToThankYouScreen: () -> Unit,
    //sharedViewModel: SharedViewModel
) {
    Scaffold(
        containerColor = Color.LightGray,
        topBar = {
            ShoppingCartTopBar(
                navigateBackToMainScreen = navigateBackToMainScreen
            )
        },
        content = { padding ->
            ShoppingCartContent(
                padding = padding,
                //sharedViewModel = sharedViewModel
            )
        },
    )
}