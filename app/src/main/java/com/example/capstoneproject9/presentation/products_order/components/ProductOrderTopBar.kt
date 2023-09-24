package com.example.capstoneproject9.presentation.products_order.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.getTopBarColors
import com.example.capstoneproject9.components.icons.BackIcon
import com.example.capstoneproject9.presentation.products_order.ProductsOrderViewModel
import kotlinx.coroutines.*

@Composable
@ExperimentalMaterial3Api
fun ProductOrderTopBar(
    navigateBack: () -> Unit,
    navigateToThankYouScreen: () -> Unit,
    orderId: String,
    viewModel: ProductsOrderViewModel = hiltViewModel(),
){

    LaunchedEffect(Unit) {
        viewModel.getPaymentInfo(orderId)
    }

    val showingDialog = remember{ mutableStateOf(false) }

    PaymentInfo {info ->
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
                        showingDialog.value = true
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



    if (showingDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showingDialog.value = false
            },
            text = {
                Text(text = "Are you sure you want to delete your order?")
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
                                    viewModel.deleteOrder(orderId)
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