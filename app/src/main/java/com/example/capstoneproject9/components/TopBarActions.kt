package com.example.capstoneproject9.components

import androidx.compose.runtime.Composable
import com.example.capstoneproject9.components.icons.SearchIcon
import com.example.capstoneproject9.components.icons.ShoppingCartIcon

@Composable
fun TopBarActions(
    onSearchIconClick: () -> Unit,
    onShoppingCartIconClick: () -> Unit
) {
    SearchIcon(
        onSearchIconClick = onSearchIconClick
    )
    ShoppingCartIcon(
        onShoppingCartIconClick = onShoppingCartIconClick
    )
}