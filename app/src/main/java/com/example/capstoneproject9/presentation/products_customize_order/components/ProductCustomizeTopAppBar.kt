package com.example.capstoneproject9.presentation.products_customize_order.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.R
import com.example.capstoneproject9.components.icons.BackIcon
import com.example.capstoneproject9.presentation.products_customize_order.ProductsCustomizeViewModel
import kotlinx.coroutines.*

@ExperimentalMaterial3Api
@Composable
fun ProductCustomizeTopAppBar(
    customizeId: String,
    navigateBack: () -> Unit,
    navigateToThankYouScreen: () -> Unit,
    viewModel: ProductsCustomizeViewModel = hiltViewModel()
){
    val showingDialog = remember{ mutableStateOf(false) }

    SmallTopAppBar(
        title = {
            Text(text = "Customize Order")
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        },
        actions = {
            IconButton(
                onClick = {
                    showingDialog.value = true
                }
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(R.color.primary)
        )
    )


    if (showingDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showingDialog.value = false
            },
            text = {
                Text(text = "Are you sure you want to delete your customize order?")
            },
            title = {
                Text(text = "Delete Order")
            },
            confirmButton = {
                Text(
                    text = "Ok",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(
                            onClick = {
                                GlobalScope.launch(Dispatchers.IO) {
                                    viewModel.deleteCustomize(customizeId)
                                    delay(3000L)
                                    withContext(Dispatchers.Main) {
                                        navigateToThankYouScreen()
                                    }
                                }
                            }
                        )
                )
            },
            dismissButton = {
                Text(
                    text = "cancel",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(
                            onClick = {
                                showingDialog.value = false
                            }
                        )
                )
            },
            textContentColor = Color.Magenta,
            shape = RectangleShape
        )
    }
}