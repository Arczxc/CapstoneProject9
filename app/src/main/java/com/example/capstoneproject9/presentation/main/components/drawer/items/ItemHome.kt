package com.example.capstoneproject9.presentation.main.components.drawer.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.capstoneproject9.components.Title
import com.example.capstoneproject9.components.cards.BannerCard
import com.example.capstoneproject9.components.icons.BrandsHorizontalContent
import com.example.capstoneproject9.components.layouts.HorizontalContent
import com.example.capstoneproject9.core.AppConstants.BRANDS_TITLE
import com.example.capstoneproject9.core.AppConstants.POPULAR_PRODUCTS_TITLE
import com.example.capstoneproject9.presentation.main.MainViewModel
import com.example.capstoneproject9.presentation.main.components.Banners
import com.example.capstoneproject9.presentation.main.components.Brands

@Composable
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
fun ItemHome(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToProductDetailsScreen: (productId: String) -> Unit,
    navigateToBrandProductsScreen: (productBrand: String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Banners { banners ->
            BannerCard(
                banner = banners.first(),
                navigateToProductDetailsScreen = navigateToProductDetailsScreen
            )
        }
        Title(POPULAR_PRODUCTS_TITLE)
        HorizontalContent(
            pagingPopularProducts = viewModel.getPopularProducts().collectAsLazyPagingItems(),
            navigateToProductDetailsScreen = navigateToProductDetailsScreen
        )
        Title(BRANDS_TITLE)
        Brands(
            brandsContent = { brands ->
                BrandsHorizontalContent(
                    brands = brands,
                    navigateToBrandProductsScreen = navigateToBrandProductsScreen
                )
            }
        )
    }
}