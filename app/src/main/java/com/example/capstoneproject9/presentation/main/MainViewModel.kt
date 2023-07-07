package com.example.capstoneproject9.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.capstoneproject9.core.Utils.Companion.items
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.Loading
import com.example.capstoneproject9.domain.model.Response.Success
import com.example.capstoneproject9.domain.repository.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: MainRepository
): ViewModel() {
    val user = repo.user

    var bannersResponse by mutableStateOf<BannersResponse>(Loading)
        private set
    var brandsResponse by mutableStateOf<BrandsResponse>(Loading)
        private set
    var ordersResponse by mutableStateOf<OrdersResponse>(Loading)
        private set
    var customizeOrderResponse by mutableStateOf<CustomizeOrderResponse>(Loading)
        private set
    var faqResponse by mutableStateOf<FAQResponse>(Loading)
        private set
    var shoppingCartSizeResponse by mutableStateOf<Response<Long>>(Loading)
        private set
    var profileInfoResponse by mutableStateOf<ProfileInfoResponse>(Loading)
        private set
    var signOutResponse by mutableStateOf<SignOutResponse>(Success(false))
        private set

    var selectedItem by mutableStateOf(items[0])

    init {
        getBanners()
        getBrands()
        getOrders()
        getCustomizeOrder()
        getFAQ()
        getProfile()
    }

    private fun getBanners() = viewModelScope.launch {
        bannersResponse = repo.getBannersFromRealtimeDatabase()
    }

    fun getPopularProducts() = repo.getPopularProductsFromFirestore().cachedIn(viewModelScope)

    private fun getBrands() = viewModelScope.launch {
        brandsResponse = repo.getBrandsFromRealtimeDatabase()
    }

    private fun getOrders() = viewModelScope.launch {
        ordersResponse = repo.getOrdersFromFirestore()
    }

    private fun getCustomizeOrder() = viewModelScope.launch {
        customizeOrderResponse = repo.getCustomizeOrderFromFirestore()
    }

    private fun getFAQ() = viewModelScope.launch {
        faqResponse = repo.getFaqFromFirestore()
    }

    private fun getProfile() = viewModelScope.launch {
        profileInfoResponse = repo.getProfileInfoInFirestore()
    }

    fun getFavoriteProducts() = repo.getFavoriteProductsFromFirestore(user.uid).cachedIn(viewModelScope)

    fun getShoppingCartSize() = viewModelScope.launch {
        repo.getShoppingCartSizeFromRealtimeDatabase().collect { response ->
            shoppingCartSizeResponse = response
        }
    }

    fun signOut() = viewModelScope.launch {
        signOutResponse = Loading
        signOutResponse = repo.signOut()
    }
}