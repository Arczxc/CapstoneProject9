package com.example.capstoneproject9.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.capstoneproject9.core.AppConstants.CUSTOMIZE_ID
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.example.capstoneproject9.core.AppConstants.NO_VALUE
import com.example.capstoneproject9.core.AppConstants.ORDER_ID
import com.example.capstoneproject9.core.AppConstants.PAYMENT_ID
import com.example.capstoneproject9.core.AppConstants.PRODUCT_BRAND
import com.example.capstoneproject9.core.AppConstants.PRODUCT_ID
import com.example.capstoneproject9.core.AppConstants.TRACKING_ID
import com.example.capstoneproject9.navigation.Screen.*
import com.example.capstoneproject9.presentation.SharedViewModel
import com.example.capstoneproject9.presentation.auth.AuthScreen
import com.example.capstoneproject9.presentation.brand_products.BrandProductsScreen
import com.example.capstoneproject9.presentation.edit_profile_info.EditProfileScreen
import com.example.capstoneproject9.presentation.main.MainScreen
import com.example.capstoneproject9.presentation.product_details.ProductDetailsScreen
import com.example.capstoneproject9.presentation.product_search.ProductSearchScreen
import com.example.capstoneproject9.presentation.products_order.ProductsOrderScreen
import com.example.capstoneproject9.presentation.shopping_cart.ShoppingCartScreen
import com.example.capstoneproject9.presentation.submit_ticket.SubmitTicketScreen
import com.example.capstoneproject9.presentation.my_ticket.MyTicketScreen
import com.example.capstoneproject9.presentation.open_3d_screen.Open3dScreen
import com.example.capstoneproject9.presentation.products_customize_order.ProductCustomizeScreen
import com.example.capstoneproject9.presentation.products_order_payment.ProductOrderPaymentScreen
import com.example.capstoneproject9.presentation.products_order_tracking.ProductOrderTrackingScreen
import com.example.capstoneproject9.presentation.upload_image.UploadImageScreen
import com.example.capstoneproject9.presentation.thank_you.ThankYouScreen

@Composable
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalFoundationApi
fun NavGraph(
    navController: NavHostController
) {
    val direction = remember(navController) { Direction(navController) }
    val sharedViewModel: SharedViewModel = viewModel()

    AnimatedNavHost(
        navController = navController,
        startDestination = AuthScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {



        composable(
            route = AuthScreen.route
        ) {
            AuthScreen(
                navigateToHomeScreen = {
                    direction.navigateToHomeScreen()
                }
            )
        }



        val uri = "https://ccwebsite121100.000webhostapp.com"
        //val uri2 = "cuddly"
        composable(
            route = MainScreen.route,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://ccwebsite121100.000webhostapp.com/{id}"

                },
            ),

        ) {

            BackHandler(true) {
                //Do Nothing
            }

            MainScreen(
                navigateToProductSearchScreen = {
                    direction.navigateToProductSearchScreen()
                },
                navigateToShoppingCartScreen = {
                    direction.navigateToShoppingCartScreen()
                },
                navigateToProductDetailsScreen = { productId ->
                    direction.navigateToProductDetailsScreen(productId)
                },
                navigateToBrandProductsScreen = { productBrand ->
                    direction.navigateToBrandProductsScreen(productBrand)
                },
                navigateToProductsOrderScreen = { orderId ->
                    direction.navigateToProductsOrderScreen(orderId)
                },
                navigateToAuthScreen = {
                    direction.navigateToAuthScreen()
                },
                navigateToSubmitTicketScreen = {
                    direction.navigateToSubmitTicketScreen()
                },
                navigateToMyTicketScreen = {
                    direction.navigateToMyTicketScreen()
                },
                navigateToEditProfileScreen = {
                    direction.navigateToEditProfile()
                },
                navigateToCustomizeOrderScreen = { customizeId ->
                    direction.navigateToCustomizeProductScreen(customizeId)
                }
            )
        }



        composable(
            route = ProductSearchScreen.route
        ) {
            ProductSearchScreen(
                navigateBack = {
                    direction.navigateBack()
                },
                navigateToProductDetailsScreen = { productId ->
                    direction.navigateToProductDetailsScreen(productId)
                }
            )
        }


        composable(
            route = EditProfileScreen.route
        ){
            EditProfileScreen(
                navigateBack = {
                    direction.navigateBack()
                },
                navigateToThankYouScreen = {
                    direction.navigateToThankYouScreen()
                }
            )
        }



        composable(
            route = ShoppingCartScreen.route
        ) {
            ShoppingCartScreen(
                navigateBackToMainScreen = {
                    direction.navigateBackToMainScreen()
                },
                navigateToThankYouScreen = {
                    direction.navigateToThankYouScreen()
                }
                //sharedViewModel    practice might delete later
            )
        }



        composable(
            route = "${ProductsOrderScreen.route}/{$ORDER_ID}",
            arguments = listOf(
                navArgument(ORDER_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getString(ORDER_ID) ?: NO_VALUE
            ProductsOrderScreen(
                orderId = orderId,
                navigateToProductSearchScreen = {
                    direction.navigateToProductSearchScreen()
                },
                navigateToShoppingCartScreen = {
                    direction.navigateToShoppingCartScreen()
                },
                navigateBack = {
                    direction.navigateBack()
                },
                navigateToPaymentDetailsScreen = { paymentId ->
                    direction.navigateToProductsOrderPaymentScreen(paymentId)
                },
                navigateToTrackingDetailsScreen = { trackingId ->
                    direction.navigateToProductsOrderTrackingScreen(trackingId)
                },
                navigateToThankYouScreen = {
                    direction.navigateToThankYouScreen()
                }
            )
        }



        composable(
            route = "${ProductsOrderPaymentScreen.route}/{$PAYMENT_ID}",
            arguments = listOf(
                navArgument(PAYMENT_ID){
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            val paymentId = backStackEntry.arguments?.getString(PAYMENT_ID) ?: NO_VALUE
            ProductOrderPaymentScreen(
                paymentId = paymentId,
                navigateBack = {
                    direction.navigateBack()
                },
            )
        }



        composable(
            route = "${ProductsOrderTrackingScreen.route}/{$TRACKING_ID}",
            arguments = listOf(
                navArgument(TRACKING_ID){
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            val trackingId = backStackEntry.arguments?.getString(TRACKING_ID) ?: NO_VALUE
            ProductOrderTrackingScreen(
                trackingId = trackingId,
                navigateBack = {
                    direction.navigateBack()
                },
            )
        }



        composable(
            route = "${CustomizeProductScreen.route}/{$CUSTOMIZE_ID}",
            arguments = listOf(
                navArgument(CUSTOMIZE_ID){
                    type = NavType.StringType
                }
            )
        ){backStackEntry ->
            val customizeId = backStackEntry.arguments?.getString(CUSTOMIZE_ID) ?: NO_VALUE
            ProductCustomizeScreen(
                customizeId = customizeId,
                navigateBack = {
                    direction.navigateBack()
                },
                navigateToThankYouScreen = {
                    direction.navigateToThankYouScreen()
                }
            )
        }



        composable(
            route = ThankYouScreen.route
        ) {
            ThankYouScreen {
                direction.navigateBackToMainScreen()
            }
            //ThankYouScreen()                //sharedViewModel    practice might delete later
        }



        composable(
            route = Open3dScreen.route
        ){
            Open3dScreen{

            }
        }



        composable(
            route = UploadImageScreen.route
        ){
            UploadImageScreen(
                navigateBack = {
                    direction.navigateBack()
                },
                navigateToThankYouScreen = {
                    direction.navigateToThankYouScreen()
                }
            )
        }



        composable(
            route = "${BrandProductsScreen.route}/{$PRODUCT_BRAND}",
            arguments = listOf(
                navArgument(PRODUCT_BRAND) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val productBrand = backStackEntry.arguments?.getString(PRODUCT_BRAND) ?: NO_VALUE
            BrandProductsScreen(
                productBrand = productBrand,
                navigateToProductSearchScreen = {
                    direction.navigateToProductSearchScreen()
                },
                navigateToShoppingCartScreen = {
                    direction.navigateToShoppingCartScreen()
                },
                navigateToProductDetailsScreen = { productId ->
                    direction.navigateToProductDetailsScreen(productId)
                },
                navigateBack = {
                    direction.navigateBack()
                },
                navigateTo3dScreen = {
                    direction.navigateTo3dScreen()
                },
                navigateToUploadScreen = {
                    direction.navigateToUploadImageScreen()
                }
            )
        }



        composable(
            route = "${ProductDetailsScreen.route}/{$PRODUCT_ID}",
            arguments = listOf(
                navArgument(PRODUCT_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString(PRODUCT_ID) ?: NO_VALUE
            ProductDetailsScreen(
                productId = productId,
                navigateToProductSearchScreen = {
                    direction.navigateToProductSearchScreen()
                },
                navigateToShoppingCartScreen = {
                    direction.navigateToShoppingCartScreen()
                },
                navigateBack = {
                    direction.navigateBack()
                }
            )
        }



        composable(
            route = SubmitTicketScreen.route
        ){
            SubmitTicketScreen(
                navigateBack = {
                    direction.navigateBack()
                },
                navigateToThankYouScreen = {
                    direction.navigateToThankYouScreen()
                }
            )
        }



        composable(
            route = MyTicketScreen.route
        ){
            MyTicketScreen(
                navigateBack = {
                    direction.navigateBack()
                },
                navigateToThankYouScreen = {
                    direction.navigateToThankYouScreen()
                }
            )
        }
    }



    // will create submit and my ticket screen composable here
}