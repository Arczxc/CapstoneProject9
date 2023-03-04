package com.example.capstoneproject9.presentation.thank_you

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.capstoneproject9.components.LargeButton
import com.example.capstoneproject9.components.Message
import com.example.capstoneproject9.core.AppConstants.BACK_TO_HOME
import com.example.capstoneproject9.core.AppConstants.THANK_YOU_MESSAGE

@Composable
fun ThankYouScreen(
    navigateBackToMainScreen: () -> Unit
) {
    Message(
        text = THANK_YOU_MESSAGE
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        LargeButton(
            text = BACK_TO_HOME,
            onClick = navigateBackToMainScreen
        )
    }
}