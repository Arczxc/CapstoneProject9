package com.example.capstoneproject9.presentation.main.components.drawer.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.capstoneproject9.components.cards.FAQCARD1
import com.example.capstoneproject9.presentation.main.components.FAQ


@Composable
@ExperimentalMaterial3Api
fun ItemFAQ(
    navigateToMyTicketScreen: () -> Unit,
    navigateToSubmitTicketScreen: () -> Unit,
){
    FAQ{ faqs ->
        Column {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(faqs) { faq ->
                    FAQCARD1(
                        faq = faq
                    )
                }
            }

            Button(onClick = { navigateToMyTicketScreen() }) {
                Text(text = "MY TICKET")
            }

            Button(onClick = { navigateToSubmitTicketScreen() }) {
                Text(text = "SUBMIT TICKET")
            }
        }
    }
}