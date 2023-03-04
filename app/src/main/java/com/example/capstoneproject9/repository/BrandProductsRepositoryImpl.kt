package com.example.capstoneproject9.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.google.firebase.firestore.FirebaseFirestore
import com.example.capstoneproject9.core.FirebaseConstants.BRAND
import com.example.capstoneproject9.core.FirebaseConstants.PAGE_SIZE
import com.example.capstoneproject9.core.FirebaseConstants.PRODUCTS
import com.example.capstoneproject9.domain.repository.BrandProductsRepository

class BrandProductsRepositoryImpl(
    private val db: FirebaseFirestore,
    private val config: PagingConfig
): BrandProductsRepository {
    override fun getBrandProductsFromFirestore(brand: String) = Pager(
        config = config
    ) {
        ProductsPagingSource(
            query = db.collection(PRODUCTS).whereEqualTo(BRAND, brand).limit(PAGE_SIZE)
        )
    }.flow
}