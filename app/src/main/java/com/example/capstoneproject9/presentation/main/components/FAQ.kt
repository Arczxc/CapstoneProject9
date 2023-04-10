package com.example.capstoneproject9.presentation.main.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.ProgressBar
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.repository.FAQ
import com.example.capstoneproject9.presentation.main.MainViewModel

@Composable
fun FAQ(
    viewModel: MainViewModel = hiltViewModel(),
    FAQContent: @Composable (faq: FAQ) -> Unit
){
    when(val faqResponse = viewModel.faqResponse) {
        is Loading -> ProgressBar()
        is Success -> faqResponse.data?.let { faq ->
            FAQContent(faq)
        }
        is Failure -> LaunchedEffect(Unit) {
            print(faqResponse.e)
        }
    }
}
