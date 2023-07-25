package com.example.capstoneproject9.presentation.my_ticket.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.my_ticket.MyTicketViewModel

@Composable
fun DeleteTicket(
    viewModel: MyTicketViewModel = hiltViewModel()
){
    when (val deleteTicketResponse = viewModel.deleteTicketResponse){
        is Loading -> Unit
        is Success -> Unit
        is Failure -> LaunchedEffect(Unit){
            print(deleteTicketResponse)
        }
    }
}