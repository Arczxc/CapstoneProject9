package com.example.capstoneproject9.presentation.edit_profile_info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.capstoneproject9.presentation.edit_profile_info.components.EditProfileContent
import com.example.capstoneproject9.presentation.edit_profile_info.components.EditProfileTopAppBar
import com.example.capstoneproject9.presentation.map.MapScreen
import com.example.capstoneproject9.presentation.thank_you.MainContent

@Composable
@ExperimentalMaterial3Api
fun EditProfileScreen(
    navigateBack: () -> Unit,
    navigateToThankYouScreen: () -> Unit,
){
    Scaffold(
        topBar = {
            EditProfileTopAppBar(
                navigateBack = navigateBack
            )
        },
        content = {padding ->
            EditProfileContent(
                padding = padding,
                navigateToThankYouScreen = navigateToThankYouScreen,
            )
        }
    )

    /*Box(modifier = Modifier.fillMaxSize()){
        EditProfileContent()
        //MapScreen()
        //MainContent(url = "https://www.google.com/maps/@14.4080896,120.946688,13z?entry=ttu")
    }*/
}