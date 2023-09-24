package com.example.capstoneproject9.presentation.brand_products

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.capstoneproject9.components.AppTopBar
import com.example.capstoneproject9.core.AppConstants.SHOES
import com.example.capstoneproject9.presentation.brand_products.components.BrandProductsContent
import java.util.*

@Composable
@ExperimentalMaterial3Api
fun BrandProductsScreen(
    productBrand: String,
    navigateToProductSearchScreen: () -> Unit,
    navigateToShoppingCartScreen: () -> Unit,
    navigateToProductDetailsScreen: (productId: String) -> Unit,
    navigateBack: () -> Unit,
    navigateTo3dScreen: () -> Unit,
    navigateToUploadScreen: () -> Unit,
) {
    Scaffold(
        containerColor = Color.LightGray,
        topBar = {
            AppTopBar(
                title = "${productBrand.capitalizeFirstChar()}",       //title = "${productBrand.capitalizeFirstChar()} $SHOES",
                navigateBack = navigateBack,
                onSearchIconClick = navigateToProductSearchScreen,
                onShoppingCartIconClick = navigateToShoppingCartScreen
            )
        },
        content = { padding ->
            BrandProductsContent(
                padding = padding,
                productBrand = productBrand,
                navigateToProductDetailsScreen = navigateToProductDetailsScreen,
                navigateTo3dScreen = navigateTo3dScreen,
                navigateToUploadScreen = navigateToUploadScreen
            )
        }
    )
}

fun String.capitalizeFirstChar() = this.replaceFirstChar { char ->
    if (char.isLowerCase()) {
        char.titlecase(Locale.getDefault())
    } else {
        char.toString()
    }
}