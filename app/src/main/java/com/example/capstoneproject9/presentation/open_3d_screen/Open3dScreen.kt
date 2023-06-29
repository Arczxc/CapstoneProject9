package com.example.capstoneproject9.presentation.open_3d_screen

import androidx.compose.runtime.Composable
import com.example.capstoneproject9.components.HyperlinkText
import com.example.capstoneproject9.presentation.thank_you.MainContent


@Composable
fun Open3dScreen(function: () -> Unit) {
    //MainContent(url = "unitydl://mylink/")
    HyperlinkText(
        fullText = "check mo tong url nato saka tong wala lang to",
        linkText = listOf("url", "wala lang to"),
        hyperlinks = listOf("unitydl://mylink/", "unitydl://mylink/")
    )
}