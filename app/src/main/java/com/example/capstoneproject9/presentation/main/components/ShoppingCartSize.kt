package com.example.capstoneproject9.presentation.main.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.core.Utils.Companion.print
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.presentation.main.MainViewModel

@Composable
fun ShoppingCartSize(
    viewModel: MainViewModel = hiltViewModel(),
    shoppingCartSizeContent: @Composable (shoppingCartSize: Long) -> Unit
) {
    when(val shoppingCartSizeResponse = viewModel.shoppingCartSizeResponse) {
        is Loading -> Unit
        is Success -> shoppingCartSizeResponse.data?.let { shoppingCartSize ->
            shoppingCartSizeContent(shoppingCartSize)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(shoppingCartSizeResponse.e)
        }
    }
}