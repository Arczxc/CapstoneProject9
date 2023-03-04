package com.example.capstoneproject9.presentation.product_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.capstoneproject9.domain.repository.ProductSearchRepository
import javax.inject.Inject

@HiltViewModel
class ProductSearchViewModel @Inject constructor(
    private val repo: ProductSearchRepository
): ViewModel() {
    fun getSearchProducts(searchText: String) = if (searchText.isEmpty()) {
        repo.getProductsFromFirestore()
    } else {
        repo.getSearchProductsFromFirestore(searchText)
    }.cachedIn(viewModelScope)
}