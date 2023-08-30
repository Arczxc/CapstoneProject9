package com.example.capstoneproject9.presentation.main.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.repository.Orders
import com.example.capstoneproject9.presentation.main.MainViewModel

@Composable
fun PurchaeHistory(
    viewModel: MainViewModel = hiltViewModel(),
    purchaseHistoryContent: @Composable (purchaseHistory: Orders) -> Unit
){
    when(val purchaseHistoryResponse = viewModel.purchaseHistoryResponse){
        is Loading -> ProgressBar()
        is Success -> purchaseHistoryResponse.data?.let { purchaseHistory ->
            purchaseHistoryContent(purchaseHistory)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(purchaseHistoryResponse.e)
        }
    }
}