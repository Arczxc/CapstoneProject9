package com.example.capstoneproject9.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.capstoneproject9.domain.model.Data
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class SharedViewModel: ViewModel() {

    val db = Firebase.firestore

    private var _saveLink:MutableState<String> = mutableStateOf("")
    var saveLink: State<String> = _saveLink
    /*private var _saveLink = MutableLiveData("")
    var saveLink: LiveData<String> = _saveLink*/

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

        val link = data!!.data.attributes.checkout_url.toString()
        _saveLink.value = data!!.data.attributes.checkout_url.toString()
        return data!!.data.attributes.checkout_url
    }

}