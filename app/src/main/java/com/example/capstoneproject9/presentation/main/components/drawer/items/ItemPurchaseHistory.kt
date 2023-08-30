package com.example.capstoneproject9.presentation.main.components.drawer.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.capstoneproject9.components.cards.OrderCard
import com.example.capstoneproject9.presentation.main.components.PurchaeHistory

@Composable
@ExperimentalMaterial3Api
fun ItemPurchaseHistory(
    navigateToProductsOrderScreen: (orderId: String) -> Unit
){
    PurchaeHistory{ purchaseHistory ->
        Column {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentPadding = PaddingValues(8.dp)
            ){
                items(purchaseHistory) { purchaseHistory ->
                    OrderCard(
                        order = purchaseHistory,
                        navigateToProductsOrderScreen = navigateToProductsOrderScreen
                    )
                }
            }
        }
    }
}