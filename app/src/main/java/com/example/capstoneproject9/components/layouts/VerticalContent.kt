package com.example.capstoneproject9.components.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.capstoneproject9.components.cards.ProductCard
import com.example.capstoneproject9.core.AppConstants.NO_VALUE
import com.example.capstoneproject9.domain.model.Product

@Composable
@ExperimentalMaterial3Api
fun VerticalContent(
    pagingProducts: LazyPagingItems<Product>,
    navigateToProductDetailsScreen: (productId: String) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(
            minSize = 128.dp
        ),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(pagingProducts.itemCount) { index ->
                val product = pagingProducts[index]
                product?.let {
                    ProductCard(
                        product = product,
                        onClick = {
                            val productId = product.id ?: NO_VALUE
                            navigateToProductDetailsScreen(productId)
                        }
                    )
                }

            }
        }
    )
}