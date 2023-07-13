package com.example.capstoneproject9.presentation.products_order_payment.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.presentation.products_order_payment.ProductsOrderPaymentViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductsOrderPaymentScreen(
    viewModel: ProductsOrderPaymentViewModel = hiltViewModel(),
    padding: PaddingValues,
    paymentId: String
){
    LaunchedEffect(Unit){
        viewModel.getPaymentDetails(paymentId)
    }

    PaymentDetails{ paymentDetails ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)){
            Column(
                modifier = Modifier.fillMaxWidth()
            ){

                val hasRequested = rememberSaveable {
                mutableStateOf(false)
            }

                Text(text = paymentDetails.paymentStatus!!)

                if (paymentDetails.paymentStatus == "unpaid"){
                    TextButton(onClick = { hasRequested.value = true }) {
                        Text("Request Refund")
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

                    val orderId = paymentDetails.orderId!!
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

    RefundRequest()
}