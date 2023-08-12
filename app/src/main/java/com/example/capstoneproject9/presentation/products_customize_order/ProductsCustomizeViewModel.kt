package com.example.capstoneproject9.presentation.products_customize_order

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject9.domain.model.Data
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.*
import com.example.capstoneproject9.domain.repository.CustomizeOrderResponses
import com.example.capstoneproject9.domain.repository.ProductsOrderRepository
import com.example.capstoneproject9.domain.repository.ProfileInfoResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class ProductsCustomizeViewModel @Inject constructor(
    private val repo: ProductsOrderRepository
): ViewModel() {

    var customizeOderResponse by mutableStateOf <CustomizeOrderResponses>(Loading)
        private set
    var createLinkResponse by mutableStateOf<Response<Boolean>>(Success(false))
        private set
    var deleteCustomizeResponse by mutableStateOf<Response<Boolean>>(Success(false))
        private set
    var profileInfoResponse by mutableStateOf<ProfileInfoResponse>(Loading)
        private set


    fun getCustomizeOrder(customizeId: String) = viewModelScope.launch {
        customizeOderResponse = repo.getCustomizeOrder(customizeId)
    }

    fun creatLink(paymongo: Data) = viewModelScope.launch {
        createLinkResponse = Loading
        createLinkResponse = repo.createLink(paymongo)
    }

    fun deleteCustomize(customizeId: String) = viewModelScope.launch {
        deleteCustomizeResponse = Loading
        deleteCustomizeResponse = repo.deleteCustomizeOrder(customizeId)
    }



    private val client = OkHttpClient()
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val dataJsonAdapter = moshi.adapter(Data::class.java)

    suspend fun getLink(price: Int): Data{
        val mediaType = "application/json".toMediaTypeOrNull()
        val body = RequestBody.create(mediaType, "{\"data\":{\"attributes\":{\"amount\":$price,\"description\":\"checkoutURL\"}}}")
        val request = Request.Builder()
            .url("https://api.paymongo.com/v1/links")
            .post(body)
            .addHeader("accept", "application/json")
            .addHeader("content-type", "application/json")
            .addHeader("authorization", "Basic c2tfbGl2ZV9MWTR6ZmN5QkRYQ29HWXROUVNzdDVmNUw6")
            .build()
        val response = client.newCall(request).execute()

        val data = dataJsonAdapter.fromJson(response.body!!.source())

        return data!!
        //data!!.data.attributes.reference_number
    }

    fun getProfile() = viewModelScope.launch {
        profileInfoResponse = repo.getProfileInfoInFirestore()
    }


}