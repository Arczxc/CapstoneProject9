package com.example.capstoneproject9.presentation.submit_ticket.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.presentation.submit_ticket.SubmitTicketViewModel

@Composable
@ExperimentalMaterial3Api
fun SubmitTicketContent(
    viewModel: SubmitTicketViewModel = hiltViewModel(),
    padding: PaddingValues
){

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            
        ){

            val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
            var expanded by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf(options[0]) }
            // We want to react on tap/press on TextField to show menu
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
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

            Spacer(
                modifier = Modifier.padding(15.dp)
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

            Button(onClick = {
                viewModel.submitTicket(selectedOptionText ,state.value)
            }) {
                Text(text = "SUBMIT TICKET")
            }
        }


    SubmitTicket()
}