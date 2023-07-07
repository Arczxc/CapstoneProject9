package com.example.capstoneproject9.presentation.products_customize_order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.repository.CustomizeOrderResponses
import com.example.capstoneproject9.domain.repository.ProductsOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsCustomizeViewModel @Inject constructor(
    private val repo: ProductsOrderRepository
): ViewModel() {

    var customizeOderResponse by mutableStateOf <CustomizeOrderResponses>(Loading)
        private set


    fun getCustomizeOrder() = viewModelScope.launch {
        customizeOderResponse = repo.getCustomizeOrder()
    }
}