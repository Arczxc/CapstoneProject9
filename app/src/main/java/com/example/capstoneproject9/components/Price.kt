package com.example.capstoneproject9.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.example.capstoneproject9.R
import com.example.capstoneproject9.core.AppConstants.DOT
import com.example.capstoneproject9.core.AppConstants.PESO

@Composable
fun Price(
    price: String,
    fontSize: TextUnit
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = colorResource(R.color.accent),
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(PESO)
            }
            withStyle(
                style = SpanStyle(
                    color = Color.DarkGray
                )
            ) {
                append(price.addCharAtIndex(price.length - 2))
            }
        },
        fontSize = fontSize,
        maxLines = 1
    )
}
fun String.addCharAtIndex(index: Int) = StringBuilder(this).apply {
    insert(index, DOT)
}.toString()