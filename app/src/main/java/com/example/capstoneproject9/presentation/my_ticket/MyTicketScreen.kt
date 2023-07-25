package com.example.capstoneproject9.presentation.my_ticket

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.capstoneproject9.presentation.my_ticket.components.MyTicketContent
import com.example.capstoneproject9.presentation.my_ticket.components.MyTicketTopBar
import com.example.capstoneproject9.presentation.submit_ticket.components.SubmitTicketTopBar


@Composable
@ExperimentalMaterial3Api
fun MyTicketScreen(
    navigateBack: () -> Unit,
    navigateToThankYouScreen: () -> Unit
){
    Scaffold(
        topBar = {
            MyTicketTopBar(
                navigateBack = navigateBack,
            )
        },
        content = { padding ->
           MyTicketContent(
               padding = padding,
               navigateToThankYouScreen = navigateToThankYouScreen
            )
        },
    )
}
