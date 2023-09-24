package com.example.capstoneproject9.presentation.product_search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.capstoneproject9.components.Message
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.components.layouts.VerticalContent
import com.example.capstoneproject9.core.AppConstants.NO_PRODUCTS_FOUND_MESSAGE
import com.example.capstoneproject9.core.AppConstants.NO_VALUE
import com.example.capstoneproject9.core.Utils.Companion.print
import com.example.capstoneproject9.presentation.product_search.components.ProductSearchTopBar

@Composable
@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
fun ProductSearchScreen(
    viewModel: ProductSearchViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToProductDetailsScreen: (productId: String) -> Unit
) {
    var search by rememberSaveable(
        stateSaver = TextFieldValue.Saver
    ) {
        mutableStateOf(TextFieldValue(NO_VALUE))
    }
    val searchText = search.text
    val pagingProducts = viewModel.getSearchProducts(searchText).collectAsLazyPagingItems()

    Scaffold(
        containerColor = Color.LightGray,
        topBar = {
            ProductSearchTopBar(
                search = search,
                onSearchTextChanged = { newSearchText ->
                    search = newSearchText
                },
                onCloseIconClick = {
                    search = TextFieldValue(NO_VALUE)
                },
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                val refresh = pagingProducts.loadState.refresh
                when {
                    refresh is Loading -> ProgressBar()
                    pagingProducts.itemCount > 0 -> VerticalContent(
                        pagingProducts = viewModel.getSearchProducts(searchText).collectAsLazyPagingItems(),
                        navigateToProductDetailsScreen = navigateToProductDetailsScreen
                    )
                    searchText.isNotEmpty() -> Message(
                        text = NO_PRODUCTS_FOUND_MESSAGE
                    )
                    refresh is Error -> print(refresh)
                }
            }
        }
    )
}