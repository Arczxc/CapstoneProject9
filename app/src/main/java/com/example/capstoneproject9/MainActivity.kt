package com.example.capstoneproject9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.capstoneproject9.navigation.NavGraph
import com.example.capstoneproject9.navigation.Screen.MainScreen
import com.example.capstoneproject9.presentation.auth.AuthViewModel
import androidx.navigation.NavHostController
import com.example.capstoneproject9.presentation.theme.CapstoneProject9Theme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<AuthViewModel>()
    @OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class,
        ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapstoneProject9Theme {
                navController = rememberAnimatedNavController()
                NavGraph(
                    navController = navController
                )
                checkAuthState()
            }
        }
    }
    private fun checkAuthState() {
        if(viewModel.isAuthenticated) {
            navigateToHomeScreen()
        }
    }

    private fun navigateToHomeScreen() = navController.navigate(MainScreen.route)
}

// Commit mo to forFinalsTry1