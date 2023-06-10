package com.example.capstoneproject9.presentation.my_ticket.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.capstoneproject9.domain.model.Ticket

@ExperimentalMaterial3Api
@Composable
fun MyTicketCard(
    ticket: Ticket
){
    Card(
        modifier = Modifier
            .fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black,
            contentColor = Color.White,
        ),
        ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = ticket.subject.toString())
            Text(text = ticket.problem.toString())
            Text(text = ticket.dateOfSubmission.toString())
        }
    }

}