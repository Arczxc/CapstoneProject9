package com.example.capstoneproject9.presentation.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capstoneproject9.R
import com.example.capstoneproject9.core.AppConstants.SIGN_IN_WITH_GOOGLE

@Composable
fun SignInButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(bottom = 48.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.accent)
        )
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.ic_google_logo
            ),
            contentDescription = null
        )
        Text(
            text = SIGN_IN_WITH_GOOGLE,
            modifier = Modifier.padding(6.dp),
            fontSize = 18.sp
        )
    }
}