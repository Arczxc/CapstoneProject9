package com.example.capstoneproject9.presentation.products_order_tracking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.repository.ProductsOrderRepository
import com.example.capstoneproject9.domain.repository.ProfileInfoResponse
import com.example.capstoneproject9.domain.repository.TrackingDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ProductsOrderTrackingViewModel @Inject constructor(
    private val repo: ProductsOrderRepository
): ViewModel() {

    var trackingDetailsResponse by mutableStateOf<TrackingDetailsResponse>(Loading)
        private set

    var profileInfoResponse by mutableStateOf<ProfileInfoResponse>(Loading)
        private set

    /*init {
        getProfile()
    }*/

    fun getTrackingDetails(trackingId: String) = viewModelScope.launch{
        trackingDetailsResponse = repo.getTrackingDetailsFromFirestore(trackingId)
    }

    fun getProfile() = viewModelScope.launch {
        profileInfoResponse = repo.getProfileInfoInFirestore()
    }

}