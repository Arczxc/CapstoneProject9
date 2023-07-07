package com.example.capstoneproject9.navigation

import com.example.capstoneproject9.core.AppConstants.AUTH_SCREEN
import com.example.capstoneproject9.core.AppConstants.BRAND_PRODUCTS_SCREEN
import com.example.capstoneproject9.core.AppConstants.CUSTOMIZE_PRODUCT_SCREEN
import com.example.capstoneproject9.core.AppConstants.EDIT_PROFILE_SCREEN
import com.example.capstoneproject9.core.AppConstants.MAIN_SCREEN
import com.example.capstoneproject9.core.AppConstants.MY_TICKET_SCREEN
import com.example.capstoneproject9.core.AppConstants.OPEN_3D_SCREEN
import com.example.capstoneproject9.core.AppConstants.PRODUCTS_ORDER_PAYMENT_SCREEN
import com.example.capstoneproject9.core.AppConstants.PRODUCTS_ORDER_SCREEN
import com.example.capstoneproject9.core.AppConstants.PRODUCTS_ORDER_TRACKING_SCREEN
import com.example.capstoneproject9.core.AppConstants.PRODUCT_DETAILS_SCREEN
import com.example.capstoneproject9.core.AppConstants.PRODUCT_SEARCH_SCREEN
import com.example.capstoneproject9.core.AppConstants.SHOPPING_CART_SCREEN
import com.example.capstoneproject9.core.AppConstants.SUBMIT_TICKET_SCREEN
import com.example.capstoneproject9.core.AppConstants.THANK_YOU_SCREEN
import com.example.capstoneproject9.core.AppConstants.UPLOAD_IMAGE_SCREEN

sealed class Screen(val route: String) {
    object AuthScreen: Screen(AUTH_SCREEN)
    object MainScreen: Screen(MAIN_SCREEN)
    object ProductSearchScreen: Screen(PRODUCT_SEARCH_SCREEN)
    object ShoppingCartScreen: Screen(SHOPPING_CART_SCREEN)
    object BrandProductsScreen: Screen(BRAND_PRODUCTS_SCREEN)
    object ProductDetailsScreen: Screen(PRODUCT_DETAILS_SCREEN)
    object ProductsOrderScreen: Screen(PRODUCTS_ORDER_SCREEN)
    object ProductsOrderPaymentScreen: Screen(PRODUCTS_ORDER_PAYMENT_SCREEN)

    object EditProfileScreen: Screen(EDIT_PROFILE_SCREEN)

    object ProductsOrderTrackingScreen: Screen(PRODUCTS_ORDER_TRACKING_SCREEN)
    object ThankYouScreen: Screen(THANK_YOU_SCREEN)

    object Open3dScreen: Screen (OPEN_3D_SCREEN)

    object MyTicketScreen: Screen(MY_TICKET_SCREEN)

    object SubmitTicketScreen: Screen(SUBMIT_TICKET_SCREEN)

    object UploadImageScreen: Screen(UPLOAD_IMAGE_SCREEN)

    object CustomizeProductScreen: Screen(CUSTOMIZE_PRODUCT_SCREEN)
}