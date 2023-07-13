package com.example.capstoneproject9.presentation.products_order.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.getTopBarColors
import com.example.capstoneproject9.components.icons.BackIcon
import com.example.capstoneproject9.presentation.products_order.ProductsOrderViewModel

@Composable
@ExperimentalMaterial3Api
fun ProductOrderTopBar(
    navigateBack: () -> Unit,
    navigateToThankYouScreen: () -> Unit,
    orderId: String,
    viewModel: ProductsOrderViewModel = hiltViewModel(),
){
    TopAppBar(
        title = {
            Text(
                text = "Product Order",
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        },
        actions = {
            IconButton(
                onClick = {
                    viewModel.deleteOrder(orderId)
                    navigateToThankYouScreen()
                }
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null
                )
            }
        },
        colors = getTopBarColors()
    )
}