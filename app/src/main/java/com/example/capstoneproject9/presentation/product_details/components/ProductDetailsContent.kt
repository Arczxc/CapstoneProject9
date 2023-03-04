package com.example.capstoneproject9.presentation.product_details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.LargeButton
import com.example.capstoneproject9.components.Price
import com.example.capstoneproject9.components.ShortDivider
import com.example.capstoneproject9.components.icons.FavoriteBorderIcon
import com.example.capstoneproject9.components.icons.FavoriteIcon
import com.example.capstoneproject9.core.AppConstants.ADD_TO_CART
import com.example.capstoneproject9.core.AppConstants.NO_VALUE
import com.example.capstoneproject9.domain.model.toShoppingCartItem
import com.example.capstoneproject9.presentation.main.MainViewModel
import com.example.capstoneproject9.presentation.product_details.ProductDetailsViewModel

@Composable
fun ProductDetailsContent(
    productDetailsViewModel: ProductDetailsViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    padding: PaddingValues,
    productId: String
) {
    val uid = mainViewModel.user.uid
    LaunchedEffect(productId) {
        productDetailsViewModel.getProduct(productId)
    }
    ProductDetails { product ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                product.apply {
                    val token = image ?: NO_VALUE
                    Box(
                        contentAlignment = Alignment.TopEnd
                    ) {
                        ProductImage(
                            productId = productId,
                            token = token
                        )
                        if (favorites != null && favorites.isNotEmpty() && favorites.contains(mainViewModel.user.uid)) {
                            FavoriteIcon(
                                onClick = {
                                    productDetailsViewModel.deleteProductFromFavorites(productId, uid)
                                }
                            )
                        } else {
                            FavoriteBorderIcon(
                                onClick = {
                                    productDetailsViewModel.addProductToFavorites(productId, uid)
                                }
                            )
                        }
                    }
                    ShortDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 8.dp
                            ),
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            text = name.toString(),
                            fontSize = 21.sp,
                            color = Color.DarkGray
                        )
                        Price(
                            price = price.toString(),
                            fontSize = 21.sp
                        )
                    }
                }
            }
            LargeButton(
                text = ADD_TO_CART,
                onClick = {
                    productDetailsViewModel.addProductToShoppingCart(product.toShoppingCartItem())
                }
            )
        }
    }

    AddProductToShoppingCart()
}