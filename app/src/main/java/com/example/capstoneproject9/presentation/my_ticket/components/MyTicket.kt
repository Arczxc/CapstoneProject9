package com.example.capstoneproject9.presentation.my_ticket.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.repository.Tickets
import com.example.capstoneproject9.presentation.my_ticket.MyTicketViewModel


@Composable
fun MyTicket(
    viewmodel: MyTicketViewModel = hiltViewModel(),
    MyTicketContents: @Composable (ticket: Tickets) -> Unit
){
    when (val myTicketResponse = viewmodel.myTicketResponse){
        is Loading -> ProgressBar()
        is Success -> myTicketResponse.data?.let{ticket ->
            MyTicketContents(ticket)
        }
        is Failure -> LaunchedEffect(Unit){
            print(myTicketResponse.e)
        }
    }
}