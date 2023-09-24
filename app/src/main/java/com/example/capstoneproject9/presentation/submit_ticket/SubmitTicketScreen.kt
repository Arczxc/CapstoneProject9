package com.example.capstoneproject9.presentation.submit_ticket

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.capstoneproject9.presentation.submit_ticket.components.SubmitTicketContent
import com.example.capstoneproject9.presentation.submit_ticket.components.SubmitTicketTopBar

@Composable
@ExperimentalMaterial3Api
fun SubmitTicketScreen(
    navigateBack: () -> Unit,
    navigateToThankYouScreen: () -> Unit
){
    Scaffold(
        containerColor = Color.LightGray,
        topBar = {
            SubmitTicketTopBar(
                navigateBack = navigateBack
            )
        },
        content = { padding ->
            SubmitTicketContent(
                padding = padding,
                navigateToThankYouScreen = navigateToThankYouScreen
            )
        },
    )
}