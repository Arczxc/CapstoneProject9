package com.example.capstoneproject9.components.icons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.capstoneproject9.components.cards.BrandCard
import com.example.capstoneproject9.domain.repository.Brands

@Composable
@ExperimentalMaterial3Api
fun BrandsHorizontalContent(
    brands: Brands,
    navigateToBrandProductsScreen: (productBrand: String) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(brands) { brand ->
            BrandCard(
                brand = brand,
                onClick = {
                    brand.name?.let { productBrand ->
                        navigateToBrandProductsScreen(productBrand)
                    }
                }
            )
        }
    }
}