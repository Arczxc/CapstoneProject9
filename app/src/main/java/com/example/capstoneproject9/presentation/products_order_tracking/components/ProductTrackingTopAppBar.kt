package com.example.capstoneproject9.presentation.products_order_tracking.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.capstoneproject9.components.icons.BackIcon

@ExperimentalMaterial3Api
@Composable
fun ProductTrackingTopAppBar(
    navigateBack: () -> Unit
){
    SmallTopAppBar(
        title = {
            Text(text = "Track your Order here")
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}