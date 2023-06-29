package com.example.capstoneproject9.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.capstoneproject9.domain.model.*
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

typealias Banners = List<Banner>
typealias BannersResponse = Response<Banners>
typealias Products = List<Product>
typealias Brands = List<Brand>
typealias BrandsResponse = Response<Brands>
typealias Orders = List<Order>
typealias FAQ = List<Faq>
typealias FAQResponse = Response<FAQ>
typealias OrdersResponse = Response<Orders>
typealias SignOutResponse = Response<Boolean>
typealias ProfileInfoResponse = Response<ProfileInfo>


interface MainRepository {
    val user: User

    suspend fun getBannersFromRealtimeDatabase(): BannersResponse

    fun getPopularProductsFromFirestore(): Flow<ProductPagingData>

    suspend fun getBrandsFromRealtimeDatabase(): BrandsResponse

    suspend fun getOrdersFromFirestore(): OrdersResponse

    suspend fun getFaqFromFirestore(): FAQResponse

    fun getFavoriteProductsFromFirestore(uid: String): Flow<ProductPagingData>

    fun getShoppingCartSizeFromRealtimeDatabase(): Flow<Response<Long>>

    suspend fun getProfileInfoInFirestore(): ProfileInfoResponse

    suspend fun signOut(): SignOutResponse
}

