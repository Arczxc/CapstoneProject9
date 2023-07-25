package com.example.capstoneproject9.presentation.products_customize_order.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@Composable
fun ProductCustomizeContent(
    viewModel: ProductsCustomizeViewModel = hiltViewModel(),
    customizeId: String,
    padding: PaddingValues,
    navigateToThankYouScreen: () -> Unit
){
    LaunchedEffect(Unit){
        viewModel.getCustomizeOrder(customizeId)
    }

    CustomizeOrders{customizeOrder ->
        if (customizeOrder.total != null){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {
                Box(modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.75f)
                    .background(Color.Blue)){
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

                    if (customizeOrder.checkOutUrl == null){
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
                    }
            }
        } else {
            Message(text = "Customize Order Waiting for approval")
        }

    }

    CreateLink()
}