package com.example.capstoneproject9.components.layouts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capstoneproject9.R

@Composable
fun AddToCartButton(
    text: String,
    onClick: () -> Unit,
    stack: Int
) {

    var enabled by remember {
        mutableStateOf(true)
    }

    if (stack == 0){
        enabled = false
    }



    Button(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.accent)
        ),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = if (enabled) text else "Out of Stock",
            modifier = Modifier.padding(4.dp),
            fontSize = 24.sp
        )
    }
}