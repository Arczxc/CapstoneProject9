package com.example.capstoneproject9.presentation.main.components.drawer.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.capstoneproject9.components.Title
import com.example.capstoneproject9.components.layouts.VerticalContent
import com.example.capstoneproject9.core.AppConstants.FAVORITE_PRODUCTS_TITLE
import com.example.capstoneproject9.presentation.main.MainViewModel

@Composable
@ExperimentalMaterial3Api
fun ItemFavorites(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToProductDetailsScreen: (productId: String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Title(FAVORITE_PRODUCTS_TITLE)
        VerticalContent(
            pagingProducts = viewModel.getFavoriteProducts().collectAsLazyPagingItems(),
            navigateToProductDetailsScreen = navigateToProductDetailsScreen
        )
    }
}