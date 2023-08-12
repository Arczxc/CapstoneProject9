package com.example.capstoneproject9.presentation.products_customize_order.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.capstoneproject9.R
import com.example.capstoneproject9.components.Hyperlink
import com.example.capstoneproject9.components.Message
import com.example.capstoneproject9.core.AppConstants
import com.example.capstoneproject9.presentation.products_customize_order.ProductsCustomizeViewModel
import kotlinx.coroutines.*

@Composable
fun ProductCustomizeContent(
    viewModel: ProductsCustomizeViewModel = hiltViewModel(),
    customizeId: String,
    padding: PaddingValues,
    navigateToThankYouScreen: () -> Unit
){
    LaunchedEffect(Unit){
        viewModel.getCustomizeOrder(customizeId)
        viewModel.getProfile()
    }

    CustomizeOrders { customizeOrder ->
        ProfileDetailsCustomize { profileDetails ->
            if (customizeOrder.checkOutUrl != null) {
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
                                Text(text = customizeOrder.checkOutUrl.toString())
                                Text(text = customizeOrder.paymentStatus.toString())
                                Button(
                                    modifier = Modifier.padding(10.dp),
                                    onClick = { },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorResource(R.color.accent)
                                    )
                                    ){
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

                                            if (customizeOrder.paymentStatus == "unpaid" ){
                                                state = 0
                                            } else if(customizeOrder.paymentStatus == "paid" ) {
                                                state = 1
                                            } else if (customizeOrder.paymentStatus == "received" ){
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

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight(0.75f)
                            .background(Color.Blue)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                                .background(Color.Black),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(customizeOrder.photoUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                    }

                }
            } else {
                Message(text = "Customize Order Waiting for approval")
            }

        }
    }
    CreateLink()
}

/*if (customizeOrder.checkOutUrl == null){
                        Box(modifier = Modifier.padding(15.dp)){
                            Column(modifier = Modifier.fillMaxWidth()) {

                                val scope = CoroutineScope(Dispatchers.IO)
                                val showingDialog = remember{ mutableStateOf(false) }
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
                                                modifier = Modifier
                                                    .padding(16.dp)
                                                    .clickable(
                                                        onClick = {
                                                            scope.launch {
                                                                val price = customizeOrder.total
                                                                val link = async {
                                                                    viewModel.getLink(price!!)
                                                                }
                                                                val paymongo = link.await()
                                                                viewModel.creatLink(paymongo)
                                                            }
                                                            //navigateToThankYouScreen() === That can cause an erro so better to remove
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


                                var enabled = rememberSaveable {
                                    mutableStateOf(true)
                                }

                                Button(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = colorResource(R.color.accent)
                                    ),
                                    enabled = enabled.value,
                                    onClick = {
                                        showingDialog.value = true
                                    }
                                ) {
                                    Text(
                                        text = AppConstants.SEND_ORDER,
                                        modifier = Modifier.padding(4.dp),
                                        fontSize = 24.sp
                                    )
                                }

                            }
                            // Will Add the createLink Function. Must Finish
                        }
                    } else {
                        Hyperlink(
                            fullText = "PAY HERE" ,
                            linkText = customizeOrder.checkOutUrl.toString()
                        )
                    }*/