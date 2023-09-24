package com.example.capstoneproject9.presentation.my_ticket.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.domain.model.Ticket
import com.example.capstoneproject9.presentation.my_ticket.MyTicketViewModel

@ExperimentalMaterial3Api
@Composable
fun MyTicketCard(
    ticket: Ticket,
    navigateToThankYouScreen: () -> Unit,
    viewModel: MyTicketViewModel = hiltViewModel(),
){
    val showingDialog = remember{ mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Red,
            contentColor = Color.White,
        ),
        ){
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
               /* modifier = Modifier.fillMaxSize(),*/
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = ticket.subject.toString())
                Text(text = ticket.problem.toString())
                Text(text = ticket.dateOfSubmission.toString())
                Text(text = ticket.ticketID.toString())
                Text(text = ticket.response.toString())
            }
            IconButton(
                modifier = Modifier,
                onClick = {
                    showingDialog.value = true
                }
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null
                )
            }

        }
        if (showingDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    showingDialog.value = false
                },
                text = {
                    Text(text = "Are you sure you want to delete your ticket?")
                },
                title = {
                    Text(text = "Delete Ticket")
                },
                confirmButton = {
                    Text(
                        text = "Ok",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable(
                                onClick = {
                                    viewModel.deleteTicket(ticket.ticketID.toString())
                                    navigateToThankYouScreen()
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
    }

    DeleteTicket()
}