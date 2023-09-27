package com.example.capstoneproject9.presentation.shopping_cart.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.capstoneproject9.R
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
    //sharedViewModel: SharedViewModel
) {
    LaunchedEffect(Unit){
        viewModel.getAddress()
    }
    val scope = CoroutineScope(Dispatchers.IO)

    ShoppingCart { items ->
        AddressInfo{info->
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


                var service by remember { mutableStateOf(false) }                    // Mode of service either Pick Up or Deliver
                var payment by remember { mutableStateOf(false) }                   // Mode of payment cash on deliver or online payment


                val showingDialog = remember{ mutableStateOf(false) }               // if true will show the dialog

                val showConfirmation = remember{ mutableStateOf(false) }     // tatanggalin, for testing purpose only

                var price = if(payment == true){                                       // will return price based on payment
                    viewModel.numberOfItemsInShoppingCart.toInt()
                } else{
                    15000
                }

                var modeOfService = if(service == true){
                    "Pick Up"
                } else{
                    "Deliver"
                }

                var modeOfPayment = if(payment == true){
                    "Online Payment"
                } else{
                    "Cash on Delivery"
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){


                    InputChip(
                        selected = payment,
                        onClick = { payment = !payment
                                  },
                        label = { Text(text = "Cash On Deliver")},
                        colors = InputChipDefaults.inputChipColors(
                            containerColor = Color.Green,
                            disabledContainerColor = Color.Red
                        )
                    )

                    InputChip(
                        selected = !payment,
                        onClick = { payment = !payment },
                        label = { Text(text = "Online Payment")},
                        colors = InputChipDefaults.inputChipColors(
                            containerColor = Color.Green,
                            disabledContainerColor = Color.Red
                        )
                    )


                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){

                    InputChip(
                        selected = service,
                        onClick = { service = !service },
                        label = { Text(text = "Deliver") },
                        colors = InputChipDefaults.inputChipColors(
                            containerColor = Color.Green,
                            disabledContainerColor = Color.Red
                        )
                    )

                    InputChip(
                        selected = !service,
                        enabled = payment,
                        onClick = { service = !service },
                        label = { Text(text = "Pick Up") },
                        colors = InputChipDefaults.inputChipColors(
                            containerColor = Color.Green,
                            disabledContainerColor = Color.Red
                        )
                    )

                }
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
                        price = price.toString(),
                        fontSize = 19.sp
                    )
                }


                    if (showingDialog.value) {
                        AlertDialog(
                            onDismissRequest = {
                                showingDialog.value = false
                            },
                            text = {
                                Text(text = "Are you sure you want to confirm your order?")
                            },
                            title = {
                                Text(text = "Confirm Order")
                            },
                            confirmButton = {
                                Text(
                                    text = "Ok",
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .clickable(
                                            onClick = {
                                                val job = scope.launch {
                                                    //val price = viewModel.numberOfItemsInShoppingCart.toInt()

                                                    val link = async {
                                                        viewModel.getLink(price)
                                                    }
                                                    val paymongo = link.await()
                                                    viewModel.addOrder(items, paymongo, modeOfPayment, modeOfService)
                                                    showConfirmation.value = true
                                                }
                                            }
                                        )
                                )
                            },
                            dismissButton = {
                                Text(
                                    text = "cancel",
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .clickable(
                                            onClick = {
                                                showingDialog.value = false
                                            }
                                        )
                                )
                            },
                            textContentColor = Color.Black,
                            containerColor = Color.Gray,
                            shape = RectangleShape
                        )
                    }

                if (showConfirmation.value){
                    AlertDialog(
                        onDismissRequest = {
                            showingDialog.value = false
                        },
                        text = {
                            Text(text = "Proceed to Orders for more info")
                        },
                        title = {
                            Text(text = "Order Succeed")
                        },
                        confirmButton = {
                            Text(
                                text = "Ok",
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .clickable(
                                        onClick = {
                                            showConfirmation.value = false
                                        }
                                    )
                            )
                        },
                        dismissButton = {
                            /*Text(
                                text = "cancel",
                                modifier = Modifier
                                    .padding(16.dp)
                                    .clickable(
                                        onClick = {
                                            showingDialog.value = false
                                        }
                                    )
                            )*/
                        },
                        textContentColor = Color.Black,
                        containerColor = Color.Gray,
                        shape = RectangleShape
                    )
                }


                    var enabled = rememberSaveable {
                        mutableStateOf(false)
                    }

                    if (info.fullAddress != null){
                        //enabled.value = true
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.accent)
                            ),
                            //enabled = enabled.value,
                            onClick = {
                                showingDialog.value = true
                            }
                        ) {
                            Text(
                                text = SEND_ORDER,
                                modifier = Modifier.padding(4.dp),
                                fontSize = 24.sp
                            )
                        }
                    } else {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.accent)
                            ),
                            //enabled = enabled.value,
                            onClick = {
                                //showingDialog.value = true
                            }
                        ) {
                            Text(
                                text = "Fill address information 1st",
                                modifier = Modifier.padding(4.dp),
                                fontSize = 24.sp
                            )
                        }
                    }
                }

            }
        }
    }

    AddOrder()
}




                   /*LargeButton(
                   text = SEND_ORDER,
                   onClick = {
                       showingDialog.value = true
                      *//* scope.launch{
                            val price = viewModel.numberOfItemsInShoppingCart.toInt()
                            //viewModel.addOrder(items)            // tatanggalin ko
                            val link = async {
                                viewModel.getLink(price)
                            }
                            val paymongo = link.await()
                            println(paymongo)
                            viewModel.addOrder(items, paymongo, state.value)
                        }
                        *//**//*val link = scope.launch {
                            val price = viewModel.numberOfItemsInShoppingCart.toInt()
                            //viewModel.addOrder(items)            // tatanggalin ko
                            val link = async {
                                sharedViewModel.getLink(price)
                            }
                            val links = link.await()
                            //println(links)
                        }*//**//*

                        //println(link.toString())
                        navigateToThankYouScreen()*//*
                    }
                )*/