package com.example.capstoneproject9.components.cards

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.shape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capstoneproject9.components.Price
import com.example.capstoneproject9.domain.model.Faq

import androidx.compose.runtime.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import com.example.capstoneproject9.R

/*@Composable
@ExperimentalMaterial3Api
fun FAQCard(
    faq: Faq
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
            //val orderId = order.id ?: AppConstants.NO_VALUE
            //navigateToProductsOrderScreen(orderId)
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            faq.apply {
                Text(
                    text = question.toString(),
                    fontSize = 14.sp
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Price(
                    price = answer.toString(),
                    fontSize = 14.sp
                )
            }
        }
    }
}*/
@ExperimentalMaterial3Api
@Composable
fun FAQCARD1(
    faq: Faq
){
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .padding(5.dp)
            .background(Color.Gray)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        onClick = {
            expandedState = !expandedState
        },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.accent)
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(padding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(6f),
                    text = faq.question.toString(),
                    //fontSize = titleFontSize,
                    //fontWeight = titleFontWeight,
                    maxLines = 1,
                   // overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)

                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expandedState) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = faq.answer.toString(),
                    //fontSize = descriptionFontSize,
                    //fontWeight = descriptionFontWeight,
                   // maxLines = descriptionMaxLines,
                    //overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}