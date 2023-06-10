package com.example.capstoneproject9.presentation.submit_ticket.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.example.capstoneproject9.components.icons.BackIcon

@ExperimentalMaterial3Api
@Composable
fun SubmitTicketTopBar(
    navigateBack: () -> Unit
){
    SmallTopAppBar(
        title = {
            Text(text = "Submit your Ticket Here")
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}