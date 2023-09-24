package com.example.capstoneproject9.presentation.submit_ticket.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.presentation.submit_ticket.SubmitTicketViewModel
import kotlinx.coroutines.*

@Composable
@ExperimentalMaterial3Api
fun SubmitTicketContent(
    viewModel: SubmitTicketViewModel = hiltViewModel(),
    navigateToThankYouScreen: () -> Unit,
    padding: PaddingValues
){

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            val options = listOf("Return/refund", "Delivery", "Order", "Customizing", "Payment", "Other")
            var expanded by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf(options[0]) }
            // We want to react on tap/press on TextField to show menu
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                TextField(
                    // The `menuAnchor` modifier must be passed to the text field for correctness.
                    modifier = Modifier.menuAnchor().padding(top = 15.dp),
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


            Spacer(
                modifier = Modifier.padding(15.dp)
            )

            val recipientName = remember { mutableStateOf("") }
            var contactNumber by rememberSaveable   { mutableStateOf("") }
            val contactNumberLimit = 11
            var isError by rememberSaveable {
                mutableStateOf(false)
            }
            fun validate(text: String){
                isError = text.length > contactNumberLimit
            }

            TextField(
                modifier = Modifier.padding(15.dp),
                value = recipientName.value,
                onValueChange = { recipientName.value = it },
                label = { Text(text = "Email")},
                singleLine = true,
                leadingIcon = {
                    Image(imageVector = Icons.Default.Email , contentDescription = null)
                }
            )


            TextField(
                value = contactNumber,
                onValueChange = {
                    if (it.length <= contactNumberLimit) contactNumber = it
                    validate(contactNumber)
                },
                label = { Text(text = "Phone Number")},
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                isError = isError,
                keyboardActions = KeyboardActions { validate(contactNumber)},
                modifier = Modifier
                    .padding(15.dp)
                    .semantics {
                        if (isError) error("Number invalid")
                    },
                supportingText = {
                    Text(
                        text = "${contactNumber.length} / $contactNumberLimit",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                    )
                },
                leadingIcon = {
                    Image(imageVector = Icons.Default.Call , contentDescription = null)
                }
            )

            val state = remember { mutableStateOf("") }
            TextField(
                value = state.value,
                onValueChange = { state.value = it },
                modifier = Modifier.height(100.dp),
                label = { Text("Describe the problem") }
            )

            Spacer(
                modifier = Modifier.padding(15.dp)
            )
            val showingDialog = remember{ mutableStateOf(false) }
            if (showingDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        showingDialog.value = false
                    },
                    text = {
                        Text(text = "Are you sure you want to submit your ticket?")
                    },
                    title = {
                        Text(text = "Submit Ticket")
                    },
                    confirmButton = {
                        Text(
                            text = "Ok",
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable(
                                    onClick = {
                                        GlobalScope.launch(Dispatchers.IO) {
                                            viewModel.submitTicket(selectedOptionText,contactNumber,recipientName.value, state.value)
                                            delay(3000L)
                                            withContext(Dispatchers.Main){
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

            val buttonEnabled = rememberSaveable {
                mutableStateOf(false)
            }

            if (
                recipientName.value != "" &&
                contactNumber.length == 11 &&
                state.value != ""
                ){
                buttonEnabled.value = true
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    enabled = buttonEnabled.value,
                    onClick = {
                        showingDialog.value = true
                    }) {
                    Text(text = "SUBMIT TICKET")
                }
            }


        }


    SubmitTicket()
}


