package com.example.capstoneproject9.presentation.products_order.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    ProductsOrder { items ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                ProfileDetails {profileDetails ->
                    Column(modifier = Modifier
                        .fillMaxWidth(),){
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            elevation = CardDefaults.cardElevation(5.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Gray
                            ),
                            border = BorderStroke(2.dp, Color.Yellow)
                        ){
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)) {
                                Text(text = profileDetails.recipientName.toString())
                                Text(text = profileDetails.fullAddress.toString())
                                Text(text = profileDetails.contactNumber.toString())

                                Box(modifier = Modifier
                                    .padding(padding)){
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ){

                                        //val titles = listOf("To Pay", "To Ship", "To Receive", "To Finish")
                                        Box(modifier = Modifier.padding()){
                                            var state by remember { mutableStateOf(0) }
                                            val titlesAndIcons = listOf(
                                                "To Pay" to R.drawable.baseline_payment_24,
                                                "To Ship" to R.drawable.baseline_local_shipping_24,
                                                "To Receive" to R.drawable.baseline_redeem_24,
                                                "To Finish" to R.drawable.baseline_done_24
                                            )
                                            Column {
                                                TabRow(selectedTabIndex = state) {
                                                    titlesAndIcons.forEachIndexed { index, (title, icon) ->
                                                        Tab(
                                                            text = { Text(title) },
                                                            icon = { Icon(
                                                                painter = painterResource(id = icon),
                                                                contentDescription = null) },
                                                            selected = state == index,
                                                            onClick = { state = index }
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
                PaymentInfo{info->
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
                    Column(modifier = Modifier
                        .fillMaxHeight()
                        .padding(padding)) {
                        /*Text(
                            text = info.checkOutUrl.toString(),
                            fontSize = 19.sp
                        )

                        Text(
                            text = info.orderId.toString(),
                            fontSize = 19.sp
                        )
                        Hyperlink(
                            fullText = "PAY HERE" ,
                            linkText = info.checkOutUrl.toString()
                        )
                        *//*HyperlinkText(
                            fullText = "check mo tong url nato saka tong wala lang to",
                            linkText = listOf("url", "wala lang to"),
                            hyperlinks = listOf(info.checkOutUrl.toString(), "https://www.google.com/")
                        )*//*

                        Spacer(
                            modifier = Modifier.weight(1f)
                        )

                        TextButton(onClick = {
                            viewModel.deleteOrder(info.orderId.toString())
                            navigateToThankYouScreen()
                        }) {
                            Text(text = "Cancel Order")
                        }


                        TextButton(onClick = {
                            navigateToPaymentDetailsScreen(orderId)
                        }) {
                            Text("Payment Details here")
                        }

                        Spacer(
                            modifier = Modifier.weight(1f)
                        )


                        TextButton(onClick = {
                            navigateToTrackingDetailsScreen(orderId)
                        }) {
                            Text("Track your order here")
                        }*/

                    }
                }



               /* ShortDivider()
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
                        text = PAID,
                        fontSize = 19.sp
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    Price(
                        price = calculateShoppingCartTotal(items).toString(),
                        fontSize = 19.sp
                    )
                }*/
            }
        }
    }
}