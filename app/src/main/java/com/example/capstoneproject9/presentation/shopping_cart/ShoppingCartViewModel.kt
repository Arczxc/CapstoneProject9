package com.example.capstoneproject9.presentation.shopping_cart

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstoneproject9.core.AppConstants.TAG
import com.example.capstoneproject9.domain.model.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.capstoneproject9.domain.model.Response
import com.example.capstoneproject9.domain.model.Response.Loading
import com.example.capstoneproject9.domain.model.Response.Success
import com.example.capstoneproject9.domain.repository.ShoppingCartItems
import com.example.capstoneproject9.domain.repository.ShoppingCartItemsResponse
import com.example.capstoneproject9.domain.repository.ShoppingCartRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
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
    var saveLink by mutableStateOf("hatdog naman")

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

    fun addOrder(items: ShoppingCartItems, reference: String) = viewModelScope.launch {
        addOrderResponse = Loading
        addOrderResponse = repo.addOrderInFirestore(items, reference)
    }


    private val client = OkHttpClient()
    val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    private val dataJsonAdapter = moshi.adapter(Data::class.java)

    suspend fun getLink(price: Int): String{
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

        return data!!.data.attributes.reference_number
    }

   /* fun creatLink(price: Int) = viewModelScope.launch(Dispatchers.IO) {
        val answer1 = async {
            getLink(price)
        }
        //Log.d(TAG,"${answer1.await()}")
    }

    suspend fun getLink(price: Int): String{
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

        return data!!.data.attributes.checkout_url
    }*/

    /*fun createLink() = viewModelScope.launch {
        launch (Dispatchers.IO){
            val mediaType = "application/json".toMediaTypeOrNull()
            val body = RequestBody.create(mediaType, "{\"data\":{\"attributes\":{\"amount\":690000,\"description\":\"checkoutURL\"}}}")
            val request = Request.Builder()
                .url("https://api.paymongo.com/v1/links")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Basic c2tfbGl2ZV9MWTR6ZmN5QkRYQ29HWXROUVNzdDVmNUw6")
                .build()
            val response = client.newCall(request).execute()

            val data = dataJsonAdapter.fromJson(response.body!!.source())

            if (data != null) {
                println(data.data.attributes.checkout_url)
            }
        }

    }*/
    /*fun createLink(): String{
        val result = viewModelScope.async {
            val mediaType = "application/json".toMediaTypeOrNull()
            val body = RequestBody.create(mediaType, "{\"data\":{\"attributes\":{\"amount\":690000,\"description\":\"checkoutURL\"}}}")
            val request = Request.Builder()
                .url("https://api.paymongo.com/v1/links")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Basic c2tfbGl2ZV9MWTR6ZmN5QkRYQ29HWXROUVNzdDVmNUw6")
                .build()
            val response = client.newCall(request).execute()

            val data = dataJsonAdapter.fromJson(response.body!!.source())
            data

            *//*if (data != null) {
                println(data.data.attributes.checkout_url)
            }*//*
        }
        result.invokeOnCompletion{
            if (it == null) {
                Log.d("ExampleViewModel", "${result.getCompleted()}")
            } else {
                Log.e("ExampleViewModel", "Error", it)
            }
        }
        val hatdog = result.getCompleted().toString()
        return hatdog
    }*/


  /*  fun createLink(){
        //val response = client.newCall(request).execute()

        GlobalScope.launch(Dispatchers.IO) {
            val mediaType = "application/json".toMediaTypeOrNull()
            val body = RequestBody.create(mediaType, "{\"data\":{\"attributes\":{\"amount\":690000,\"description\":\"checkoutURL\"}}}")
            val request = Request.Builder()
                .url("https://api.paymongo.com/v1/links")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Basic c2tfbGl2ZV9MWTR6ZmN5QkRYQ29HWXROUVNzdDVmNUw6")
                .build()
            val response = client.newCall(request).execute()

            val data = dataJsonAdapter.fromJson(response.body!!.source())
            if (data != null) {
                println(data.data.attributes.checkout_url)
            }

            //Log.d(TAG, response.body!!.string())

            // getLink(response)
        }
    }*/

  /*  fun createLink(){

        val mediaType = "application/json".toMediaTypeOrNull()
        val body = RequestBody.create(mediaType, "{\"data\":{\"attributes\":{\"amount\":780000,\"description\":\"checkoutURL\"}}}")
        val request = Request.Builder()
            .url("https://api.paymongo.com/v1/links")
            .post(body)
            .addHeader("accept", "application/json")
            .addHeader("content-type", "application/json")
            .addHeader("authorization", "Basic c2tfbGl2ZV9MWTR6ZmN5QkRYQ29HWXROUVNzdDVmNUw6")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: okhttp3.Response) {
                response.use {
                    //println(response.body!!.string())
                    val data = dataJsonAdapter.fromJson(response.body!!.source())
                    println(data?.data!!.attributes.checkout_url)
                }
            }
        })
    }*/

   /* fun createLink(){


        //val response = client.newCall(request).execute()

        GlobalScope.launch(Dispatchers.IO) {
            val mediaType = "application/json".toMediaTypeOrNull()
            val body = RequestBody.create(mediaType, "{\"data\":{\"attributes\":{\"amount\":690000,\"description\":\"checkoutURL\"}}}")
            val request = Request.Builder()
                .url("https://api.paymongo.com/v1/links")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Basic c2tfbGl2ZV9MWTR6ZmN5QkRYQ29HWXROUVNzdDVmNUw6")
                .build()
            val response = client.newCall(request).execute()


            Log.d(TAG, response.body!!.string())

           // getLink(response)
        }
    }*/

}