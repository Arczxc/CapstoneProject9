package com.example.capstoneproject9.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import com.example.capstoneproject9.components.DrawerTopBar
import com.example.capstoneproject9.core.Utils.Companion.items
import com.example.capstoneproject9.presentation.main.components.SignOut
import com.example.capstoneproject9.presentation.main.components.drawer.DrawerContent
import com.example.capstoneproject9.presentation.main.components.drawer.items.*
import com.example.capstoneproject9.presentation.map.MapScreen

@Composable
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    navigateToProductSearchScreen: () -> Unit,
    navigateToShoppingCartScreen: () -> Unit,
    navigateToProductDetailsScreen: (productId: String) -> Unit,
    navigateToBrandProductsScreen: (productBrand: String) -> Unit,
    navigateToProductsOrderScreen: (orderId: String) -> Unit,
    navigateToAuthScreen: () -> Unit,
    navigateToSubmitTicketScreen: () -> Unit,
    navigateToMyTicketScreen: () -> Unit,
    navigateToEditProfileScreen: () -> Unit,
    navigateToCustomizeOrderScreen: (customizeId: String) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                user = viewModel.user,
                drawerState = drawerState,
                coroutineScope = coroutineScope
            )
        },
        content = {
            Scaffold(
                containerColor = Color.LightGray,
                topBar = {
                    DrawerTopBar(
                        openNavigationDrawer = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        },
                        onSearchIconClick = navigateToProductSearchScreen,
                        onShoppingCartIconClick = navigateToShoppingCartScreen
                    )
                },
                content = { padding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        when (viewModel.selectedItem) {
                            items[0] -> ItemHome(
                                navigateToProductDetailsScreen = navigateToProductDetailsScreen,
                                navigateToBrandProductsScreen = navigateToBrandProductsScreen
                            )
                            items[1] -> ItemOrders(
                                navigateToProductsOrderScreen = navigateToProductsOrderScreen
                            )
                            items[2] -> ItemCustomizeOrder(
                                navigateToCustomizeOrderScreen = navigateToCustomizeOrderScreen
                            )
                            items[3] -> ItemPurchaseHistory(
                                navigateToProductsOrderScreen = navigateToProductsOrderScreen
                            )

                            items[4] -> ItemFavorites(
                                navigateToProductDetailsScreen = navigateToProductDetailsScreen
                            )
                            items[5] -> ItemProfile(
                                user = viewModel.user,
                                navigateToEditProfileScreen = navigateToEditProfileScreen
                            )
                            items[6] -> ItemFAQ(
                                navigateToMyTicketScreen = navigateToMyTicketScreen,
                                navigateToSubmitTicketScreen = navigateToSubmitTicketScreen
                            )
                            items[7] -> viewModel.signOut()
                        }
                    }
                }
            )
        }
    )

    SignOut { signedOut ->
        if (signedOut) {
            navigateToAuthScreen()
        }
    }
}