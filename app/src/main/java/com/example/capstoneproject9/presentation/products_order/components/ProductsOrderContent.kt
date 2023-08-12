package com.example.capstoneproject9.presentation.products_order.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.R
import com.example.capstoneproject9.components.Hyperlink
import com.example.capstoneproject9.components.HyperlinkText
import com.example.capstoneproject9.components.Price
import com.example.capstoneproject9.components.ShortDivider
import com.example.capstoneproject9.components.cards.OrderItemCard
import com.example.capstoneproject9.core.Utils.Companion.calculateShoppingCartTotal
import com.example.capstoneproject9.presentation.products_order.ProductsOrderViewModel
import com.example.capstoneproject9.presentation.products_order_payment.components.PaymentDetails
import com.example.capstoneproject9.presentation.products_order_tracking.components.ProfileDetails
import com.example.capstoneproject9.presentation.products_order_tracking.components.TrackingDetails
import kotlinx.coroutines.*

@Composable
@ExperimentalMaterial3Api
fun ProductsOrderContent(
    viewModel: ProductsOrderViewModel = hiltViewModel(),
    padding: PaddingValues,
    orderId: String,
    navigateToPaymentDetailsScreen: (paymentId: String) -> Unit,
    navigateToTrackingDetailsScreen: (trackingId: String) -> Unit,
    navigateToThankYouScreen: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getOrderShoppingCartItems(orderId)
        viewModel.getPaymentInfo(orderId)
        viewModel.getProfile()

    }
    val scope = CoroutineScope(Dispatchers.IO)

    PaymentInfo { info ->
        ProductsOrder { items ->
            ProfileDetails { profileDetails ->

                if (items.isEmpty()){

                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                elevation = CardDefaults.cardElevation(5.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.Gray
                                ),
                                border = BorderStroke(2.dp, Color.Yellow)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp)
                                ) {
                                    Text(text = profileDetails.recipientName.toString())
                                    Text(text = profileDetails.fullAddress.toString())
                                    Text(text = profileDetails.contactNumber.toString())
                                    Text(text = info.checkOutUrl.toString())
                                    Text(text = info.orderId.toString())
                                    Text(text = info.paymentStatus.toString())
                                    Button(
                                        modifier = Modifier.padding(10.dp),
                                        onClick = {
                                            GlobalScope.launch(Dispatchers.IO) {
                                                val referenceNumber = info.orderId.toString()
                                                val orderId = info.orderId.toString()
                                                val link = async {
                                                    viewModel.updateLink(referenceNumber)
                                                }
                                                val paymongo = link.await()
                                                viewModel.updatePayment(orderId, paymongo)
                                            }
                                           /* val job = scope.launch {
                                                val referenceNumber = info.orderId.toString()
                                                val orderId = info.orderId.toString()
                                                val link = async {
                                                    viewModel.updateLink(referenceNumber)
                                                }
                                                val paymongo = link.await()
                                                viewModel.updatePayment(orderId, paymongo)
                                            }*/
                                        },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = colorResource(R.color.accent)
                                        )
                                    ) {
                                        Text(text = "UPDATE PAYMENT")
                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(padding)
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxWidth()
                                        ) {

                                            //val titles = listOf("To Pay", "To Ship", "To Receive", "To Finish")
                                            Box(modifier = Modifier.padding()) {
                                                var state by remember { mutableStateOf(0) }
                                                val titlesAndIcons = listOf(
                                                    "To Pay" to R.drawable.baseline_payment_24,
                                                    "To Ship" to R.drawable.baseline_local_shipping_24,
                                                    "To Receive" to R.drawable.baseline_redeem_24,
                                                    "To Finish" to R.drawable.baseline_done_24
                                                )

                                                if (info.paymentStatus == "unpaid" ){
                                                    state = 0
                                                } else if(info.paymentStatus == "paid" ) {
                                                    state = 1
                                                } else if (info.trackingStatus == "received" ){
                                                    state = 2
                                                } else {
                                                    state = 3
                                                }


                                                Column {
                                                    TabRow(selectedTabIndex = state, contentColor = Color.Black, containerColor = Color.White) {
                                                        titlesAndIcons.forEachIndexed { index, (title, icon) ->
                                                            Tab(
                                                                text = { Text(title) },
                                                                icon = {
                                                                    Icon(
                                                                        painter = painterResource(id = icon),
                                                                        contentDescription = null
                                                                    )
                                                                },
                                                                selected = state == index,
                                                                onClick = { state = index },
                                                                enabled = false,
                                                                selectedContentColor = Color.Green,
                                                                unselectedContentColor = Color.Black
                                                            )
                                                        }
                                                    }
                                                    Text(
                                                        modifier = Modifier.align(Alignment.CenterHorizontally),
                                                        text = "Text and icon tab ${state + 1} selected",
                                                        // style = MaterialTheme.typography.body1
                                                    )
                                                }
                                            }

                                            //Text(text = trackingDetails.trackingStatus.toString())
                                        }
                                    }

                                }
                            }
                        }
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth(),
                            //.wrapContentHeight(),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            items(items) { shoppingCartItem ->
                                OrderItemCard(
                                    item = shoppingCartItem
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
                                    top = 16.dp
                                )
                        ) {
                            Text(
                                text = "Total",
                                fontSize = 19.sp
                            )
                            Spacer(
                                modifier = Modifier.weight(1f)
                            )

                            Price(
                                price = calculateShoppingCartTotal(items).toString(),
                                fontSize = 19.sp
                            )
                        }


                        val hasRequested = rememberSaveable {
                            mutableStateOf(false) }

                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
                            if (info.paymentStatus == "unpaid"){
                                Hyperlink(
                                    fullText = "PAY HERE" ,
                                    linkText = info.checkOutUrl.toString()
                                )
                            } else if (info.paymentStatus == "paid"){

                                TextButton(onClick = { hasRequested.value = true }) {
                                    Text("Request Refund")
                                }

                            }
                        }


                        if (hasRequested.value == true){

                            val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
                            var expanded by remember { mutableStateOf(false) }
                            var selectedOptionText by remember { mutableStateOf(options[0]) }

                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = !expanded },
                            ){
                                TextField(
                                    // The `menuAnchor` modifier must be passed to the text field for correctness.
                                    modifier = Modifier.menuAnchor(),
                                    readOnly = true,
                                    value = selectedOptionText,
                                    onValueChange = {},
                                    label = { Text("Label") },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                ) {
                                    options.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            text = { Text(selectionOption) },
                                            onClick = {
                                                selectedOptionText = selectionOption
                                                expanded = false
                                            },
                                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                        )
                                    }
                                }
                            }

                            val orderId = info.orderId!!
                            val reason = selectedOptionText

                            Button(
                                //enabled = enabled.value,
                                onClick = {
                                    viewModel.getRefundRequest(orderId, selectedOptionText)
                                }
                            ) {
                                Text("Submit Request Refund")
                            }
                        }

                    }
                }
            }
        }
    }
}