package com.example.capstoneproject9.presentation.shopping_cart.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.capstoneproject9.components.LargeButton
import com.example.capstoneproject9.components.Message
import com.example.capstoneproject9.components.Price
import com.example.capstoneproject9.components.ShortDivider
import com.example.capstoneproject9.components.cards.ShoppingCartItemCard
import com.example.capstoneproject9.core.AppConstants.EMPTY_CART_MESSAGE
import com.example.capstoneproject9.core.AppConstants.SEND_ORDER
import com.example.capstoneproject9.core.AppConstants.TOTAL_PAYMENT
import com.example.capstoneproject9.core.Utils.Companion.calculateShoppingCartTotal
import com.example.capstoneproject9.presentation.shopping_cart.ShoppingCartViewModel
import kotlinx.coroutines.*

@Composable
@ExperimentalMaterial3Api
fun ShoppingCartContent(
    viewModel: ShoppingCartViewModel = hiltViewModel(),
    padding: PaddingValues,
    navigateToThankYouScreen: () -> Unit
    //sharedViewModel: SharedViewModel
) {
    val scope = CoroutineScope(Dispatchers.IO)

    ShoppingCart { items ->
        viewModel.numberOfItemsInShoppingCart = calculateShoppingCartTotal(items)

        if (items.isEmpty()) {
            Message(
                text = EMPTY_CART_MESSAGE
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(items) { shoppingCartItem ->
                        ShoppingCartItemCard(
                            item = shoppingCartItem,
                            incrementQuantity = { itemId ->
                                viewModel.incrementQuantity(itemId)
                            },
                            decrementQuantity = { itemId ->
                                viewModel.decrementQuantity(itemId)
                            }
                        )
                    }
                }
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                ShortDivider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        )
                ) {
                    Text(
                        text = TOTAL_PAYMENT,
                        fontSize = 19.sp
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Price(
                        price = viewModel.numberOfItemsInShoppingCart.toString(),
                        fontSize = 19.sp
                    )
                }
                LargeButton(
                    text = SEND_ORDER,
                    onClick = {

                        scope.launch{
                            val price = viewModel.numberOfItemsInShoppingCart.toInt()
                            //viewModel.addOrder(items)            // tatanggalin ko
                            val link = async {
                                viewModel.getLink(price)
                            }
                            val paymongo = link.await()
                            println(paymongo)
                            viewModel.addOrder(items, paymongo)
                        }
                        /*val link = scope.launch {
                            val price = viewModel.numberOfItemsInShoppingCart.toInt()
                            //viewModel.addOrder(items)            // tatanggalin ko
                            val link = async {
                                sharedViewModel.getLink(price)
                            }
                            val links = link.await()
                            //println(links)
                        }*/

                        //println(link.toString())
                        navigateToThankYouScreen()
                    }
                )
            }
        }
    }

    AddOrder()
}
