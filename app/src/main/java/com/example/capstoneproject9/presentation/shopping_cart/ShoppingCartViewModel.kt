package com.example.capstoneproject9.presentation.shopping_cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.Loading
import com.example.capstoneproject9.domain.model.Response.Success
import com.example.capstoneproject9.domain.repository.ShoppingCartItems
import com.example.capstoneproject9.domain.repository.ShoppingCartItemsResponse
import com.example.capstoneproject9.domain.repository.ShoppingCartRepository
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val repo: ShoppingCartRepository
): ViewModel() {
    var shoppingCartItemsResponse by mutableStateOf<ShoppingCartItemsResponse>(Loading)
        private set
    private var incrementQuantityResponse by mutableStateOf<Response<Boolean>>(Loading)
    private var decrementQuantityResponse by mutableStateOf<Response<Boolean>>(Loading)
    var addOrderResponse by mutableStateOf<Response<Boolean>>(Success(false))
        private set
    var numberOfItemsInShoppingCart by mutableStateOf(0)

    init {
        getShoppingCartItems()
    }

    private fun getShoppingCartItems() = viewModelScope.launch {
        repo.getShoppingCartItemsFromFirestore().collect { response ->
            shoppingCartItemsResponse = response
        }
    }

    fun incrementQuantity(itemId: String) = viewModelScope.launch {
        incrementQuantityResponse = repo.incrementQuantity(itemId)
    }

    fun decrementQuantity(itemId: String) = viewModelScope.launch {
        decrementQuantityResponse = repo.decrementQuantity(itemId)
    }

    fun addOrder(items: ShoppingCartItems) = viewModelScope.launch {
        addOrderResponse = Loading
        addOrderResponse = repo.addOrderInFirestore(items)
    }
}