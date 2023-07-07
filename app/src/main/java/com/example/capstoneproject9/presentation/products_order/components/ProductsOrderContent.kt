package com.example.capstoneproject9.presentation.products_order.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.HyperlinkText
import com.example.capstoneproject9.components.Price
import com.example.capstoneproject9.components.ShortDivider
import com.example.capstoneproject9.components.cards.OrderItemCard
import com.example.capstoneproject9.core.Utils.Companion.calculateShoppingCartTotal
import com.example.capstoneproject9.presentation.products_order.ProductsOrderViewModel
import com.example.capstoneproject9.presentation.products_order_payment.components.PaymentDetails
import com.example.capstoneproject9.presentation.products_order_tracking.components.TrackingDetails

@Composable
@ExperimentalMaterial3Api
fun ProductsOrderContent(
    viewModel: ProductsOrderViewModel = hiltViewModel(),
    padding: PaddingValues,
    orderId: String,
    navigateToPaymentDetailsScreen: (paymentId: String) -> Unit,
    navigateToTrackingDetailsScreen: (trackingId: String) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.getOrderShoppingCartItems(orderId)
        viewModel.getPaymentInfo(orderId)
    }

    ProductsOrder { items ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
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
                        text = info.paymentStatus.toString(),
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
                    Column(modifier = Modifier.fillMaxHeight()) {
                        Text(
                            text = info.checkOutUrl.toString(),
                            fontSize = 19.sp
                        )

                        Text(
                            text = info.orderId.toString(),
                            fontSize = 19.sp
                        )
                        HyperlinkText(
                            fullText = "check mo tong url nato saka tong wala lang to",
                            linkText = listOf("url", "wala lang to"),
                            hyperlinks = listOf(info.checkOutUrl.toString(), "https://www.google.com/")
                        )

                        Spacer(
                            modifier = Modifier.weight(1f)
                        )


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
                        }

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