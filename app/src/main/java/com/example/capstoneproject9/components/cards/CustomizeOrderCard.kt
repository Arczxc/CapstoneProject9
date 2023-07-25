package com.example.capstoneproject9.components.cards

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capstoneproject9.components.Price
import com.example.capstoneproject9.domain.model.CustomizeOrder

@Composable
@ExperimentalMaterial3Api
fun CustomizeOrderCard(
    customizeOrder: CustomizeOrder,
    navigateToCustomizeOrderScreen: (customizeId: String) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = {
            navigateToCustomizeOrderScreen(customizeOrder.nameInStorage.toString())
        }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            customizeOrder.apply {
                Text(
                    text = dateOfSubmission!!.toLocaleString(),
                    fontSize = 14.sp
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                if (total == null){
                    Price(
                        price = "00.00",
                        fontSize = 14.sp
                    )
                } else {
                    Price(
                        price = total.toString(),
                        fontSize = 14.sp
                    )
                }

            }
        }
    }
}