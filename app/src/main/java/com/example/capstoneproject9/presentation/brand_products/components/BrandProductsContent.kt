package com.example.capstoneproject9.presentation.brand_products.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.capstoneproject9.components.cards.D3Card
import com.example.capstoneproject9.components.layouts.VerticalContent
import com.example.capstoneproject9.presentation.brand_products.BrandProductsViewModel

@Composable
@ExperimentalMaterial3Api
fun BrandProductsContent(
    viewModel: BrandProductsViewModel = hiltViewModel(),
    padding: PaddingValues,
    productBrand: String,
    navigateToProductDetailsScreen: (productId: String) -> Unit,
    navigateTo3dScreen : () -> Unit,
    navigateToUploadScreen : () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        //contentAlignment = Alignment.Center
    ) {
        Column {
            VerticalContent(
                pagingProducts = viewModel.getBrandProducts(productBrand).collectAsLazyPagingItems(),
                navigateToProductDetailsScreen = navigateToProductDetailsScreen
            )

            //D3Card(navigateTo3dScreen)
            D3Card(navigateToUploadScreen)
        }


    }
}