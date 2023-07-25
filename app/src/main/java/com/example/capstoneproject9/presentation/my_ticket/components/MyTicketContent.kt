package com.example.capstoneproject9.presentation.my_ticket.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.capstoneproject9.components.cards.FAQCARD1
import com.example.capstoneproject9.domain.repository.Tickets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTicketContent (
    padding: PaddingValues,
    navigateToThankYouScreen: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding),

        ){
        MyTicket{ ticket->
            Spacer(modifier = Modifier.padding(15.dp))
            Column {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(ticket) { ticketS->
                        MyTicketCard(
                            ticket = ticketS,
                            navigateToThankYouScreen = navigateToThankYouScreen
                        )
                    }
                }

            }

        }
    }



}