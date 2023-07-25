package com.example.capstoneproject9.navigation

import androidx.navigation.NavHostController
import com.example.capstoneproject9.navigation.Screen.*

class Direction(
    navController: NavHostController
) {
    val navigateToHomeScreen: () -> Unit = {
        navController.navigate(MainScreen.route)
    }

    val navigateToAuthScreen: () -> Unit = {
        navController.popBackStack()
        navController.navigate(AuthScreen.route)
    }

    val navigateToProductSearchScreen: () -> Unit = {
        navController.navigate(ProductSearchScreen.route)
    }

    val navigateToShoppingCartScreen: () -> Unit = {
        navController.navigate(ShoppingCartScreen.route)
    }

    val navigateToThankYouScreen: () -> Unit = {
        navController.navigate(ThankYouScreen.route)
    }

    val navigateTo3dScreen: () -> Unit = {
        navController.navigate(Open3dScreen.route)
    }

    val navigateToUploadImageScreen : () -> Unit = {
        navController.navigate(UploadImageScreen.route)
    }

    val navigateBackToMainScreen: () -> Unit = {
        navController.navigate(MainScreen.route) {
            popUpTo(MainScreen.route) {
                inclusive = true
            }
        }
    }

    val navigateToMyTicketScreen: () -> Unit = {
        navController.navigate(MyTicketScreen.route)
    }

    val navigateToEditProfile: () -> Unit = {
        navController.navigate(EditProfileScreen.route)
    }

    val navigateToSubmitTicketScreen: () -> Unit = {
        navController.navigate(SubmitTicketScreen.route)
    }

    val navigateToCustomizeProductScreen: (String) -> Unit = { customizeId ->
        navController.navigate("${CustomizeProductScreen.route}/$customizeId")                                                         //(CustomizeProductScreen.route)
    }

    val navigateToBrandProductsScreen: (String) -> Unit = { productBrand ->
        navController.navigate("${BrandProductsScreen.route}/$productBrand")
    }

    val navigateToProductsOrderScreen: (String) -> Unit = { orderId ->
        navController.navigate("${ProductsOrderScreen.route}/$orderId")
    }

    val navigateToProductsOrderPaymentScreen: (String) -> Unit = { paymentId ->
        navController.navigate("${ProductsOrderPaymentScreen.route}/$paymentId")
    }

    val navigateToProductsOrderTrackingScreen: (String) -> Unit = { trackingId ->
        navController.navigate("${ProductsOrderTrackingScreen.route}/$trackingId")
    }

    val navigateToProductDetailsScreen: (String) -> Unit = { productId ->
        navController.navigate("${ProductDetailsScreen.route}/$productId")
    }

    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }
}