package com.example.capstoneproject9.presentation.products_customize_order.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.capstoneproject9.components.icons.BackIcon

@ExperimentalMaterial3Api
@Composable
fun ProductCustomizeTopAppBar(
    navigateBack: () -> Unit
){
    SmallTopAppBar(
        title = {
            Text(text = "Customize Order")
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