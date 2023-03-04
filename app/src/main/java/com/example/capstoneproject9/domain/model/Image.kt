package com.example.capstoneproject9.domain.model

import com.example.capstoneproject9.core.FirebaseConstants.BANNERS
import com.example.capstoneproject9.core.FirebaseConstants.BRANDS
import com.example.capstoneproject9.core.FirebaseConstants.IMAGES
import com.example.capstoneproject9.core.FirebaseConstants.THUMBS

sealed class Image(val folder: String) {
    object BannerImage: Image(BANNERS)
    object BrandImage: Image(BRANDS)
    object ProductThumbImage: Image(THUMBS)
    object ProductImageImage: Image(IMAGES)
}