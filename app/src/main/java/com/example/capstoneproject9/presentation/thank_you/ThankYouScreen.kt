package com.example.capstoneproject9.presentation.thank_you

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.capstoneproject9.components.LargeButton
import com.example.capstoneproject9.components.Message
import com.example.capstoneproject9.core.AppConstants.BACK_TO_HOME
import com.example.capstoneproject9.core.AppConstants.THANK_YOU_MESSAGE

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.presentation.SharedViewModel
import com.example.capstoneproject9.presentation.shopping_cart.ShoppingCartViewModel

@Composable
fun ThankYouScreen(
    //link: String,
    //sharedViewModel: SharedViewModel
    navigateBackToMainScreen: () -> Unit
) {

    val thatText =  "Hello"                                         //sharedViewModel.saveLink.value
    println(thatText)
    if (thatText != null) {
        MainContent(url = thatText)
        /*Message(
            text = thatText
        )*/
    }
    /*Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
       *//* LargeButton(
            text = BACK_TO_HOME,
            onClick = navigateBackToMainScreen
        )*//*
    }*/
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent(url: String) {
    Scaffold(
        topBar = {
            TopAppBar( title = { Text(url) }) },
        content = { MyContent(url) }
    )
}

// Creating a composable
// function to create WebView
// Calling this function as
// content in the above function
@Composable
fun MyContent(url: String){

    // Declare a string that contains a url
    val url = "https://pm.link/marcisoven/idXYdqZ"


    // Adding a WebView inside AndroidView
    // with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}