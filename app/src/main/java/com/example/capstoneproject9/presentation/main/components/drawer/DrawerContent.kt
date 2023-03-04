package com.example.capstoneproject9.presentation.main.components.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import kotlinx.coroutines.CoroutineScope
import com.example.capstoneproject9.R
import com.example.capstoneproject9.domain.model.User

@Composable
@ExperimentalMaterial3Api
fun DrawerContent(
    drawerState: DrawerState,
    coroutineScope: CoroutineScope,
    user: User
) {
    ModalDrawerSheet(
        drawerContainerColor = colorResource(R.color.primary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            DrawerHeader(
                user = user
            )
            DrawerDivider()
            DrawerBody(
                drawerState = drawerState,
                coroutineScope = coroutineScope
            )
        }
    }
}