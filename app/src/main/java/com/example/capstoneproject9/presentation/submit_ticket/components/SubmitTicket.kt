package com.example.capstoneproject9.presentation.submit_ticket.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.core.Utils
import com.example.capstoneproject9.domain.model.Response.*

import com.example.capstoneproject9.presentation.submit_ticket.SubmitTicketViewModel


@Composable
fun SubmitTicket(
    viewModel: SubmitTicketViewModel = hiltViewModel()
){
    when(val submitTicketResponse = viewModel.submitTicketResponse) {
        is Loading -> Unit
        is Success -> Unit
        is Failure -> LaunchedEffect(Unit) {
            Utils.Companion.print(submitTicketResponse.e)
        }
    }
}