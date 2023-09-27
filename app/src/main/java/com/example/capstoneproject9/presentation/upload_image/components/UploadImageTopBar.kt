package com.example.capstoneproject9.presentation.upload_image.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.capstoneproject9.R
import com.example.capstoneproject9.core.AppConstants
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadImageTopAppBar(
    navigateBack: () -> Unit
){

    val showTooltip = remember{ mutableStateOf(true) }

    TopAppBar(
        title = {
            Text(
                text = "ADD 3D",
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = navigateBack
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    showTooltip.value = true
                }
            ) {
                Icon(
                    Icons.Default.Info,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(R.color.primary),
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )

    if (showTooltip.value) {
        AlertDialog(
            onDismissRequest = {
                showTooltip.value = false
            },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Text(
                        text =
                        "STEP 1: Download and Run the 3D Customizing \n" +
                                "\n" +
                                "\t1.If you don't already have the 3D customization  on your device, click the Download button and run it.\n" +
                                "\n" +
                                "\t2.Once the download and installation are complete, open the application.\n" +
                                "\n" +
                                "STEP 2: Customize Your Preferred Color and Design\n" +
                                "\n" +
                                "\t1.Inside the customization, navigate to the customization section and choose whatever you want to customize.\n" +
                                "\n" +
                                "\t2. Explore the customization options available. Typically, you'll have choices for colors, textures, and design elements.\n" +
                                "\n" +
                                "\t3.Select your preferred color and design options. Use the customization buttons to apply these choices to your desired product or item.\n" +
                                "\n" +
                                "STEP 3 Save Your Design\n" +
                                "\n" +
                                "\t1.Take a screenshot of your customized design. Depending on your device, you can usually do this by pressing a combination of buttons like Volume Down + Power button on smartphones or using keyboard shortcuts on computers.\n" +
                                "\n" +
                                "STEP 4: Upload the Screenshot\n" +
                                "\n" +
                                "\t1.After taking the screenshot, exit the customization section and return to the ordering or purchasing page within the customization.\n" +
                                "\n" +
                                "\t2.Look for an option to upload or attach the screenshot you just took. This option is labeled as \"Upload\" or \"Open Gallery\" and is can be found in the order customization or checkout process.\n" +
                                "\n" +
                                "\t3.Click on the \"Open Gallery\" button, and then select the screenshot from your device's gallery.\n" +
                                "\n" +
                                "STEP 5: Wait for Admin Confirmation\n" +
                                "\n" +
                                "\t1.Once you've uploaded the screenshot, wait for the administrator approval to be able to pay for your order.They may contact you for clarification if needed.\n" +
                                "\n" +
                                "\t2.After the approval review your order details, review the total amount to be paid, including shipping fees if for delivery.\n" +
                                "\n" +
                                "STEP 6: Pay for Your Order\n" +
                                "\n" +
                                "\t1.Once your customization is approved by the admin, Follow the provided payment instructions to pay the amount for your order."
                    )

                    /*Text(text = "1. Step 2 naman to")
                    Text(text = "1. Step 3 sa baba ng step 2")
                    Text(text = "1. Step 4 tapos ang kasunod Step 5")
                    Text(text = "1. Step 5 pero pede pa mag ka Step 6")*/
                }

            },
            title = {
                Text(text = "Read Me")
            },
            confirmButton = {
                Text(
                    text = "Ok",
                    color = Color.Black,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(
                            onClick = {
                                showTooltip.value = false
                            }
                        )
                )
            },
            dismissButton = {
                /*Text(
                    text = "cancel",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(
                            onClick = {
                                showTooltip.value = false
                            }
                        )
                )*/
            },
            textContentColor = Color.White,
            containerColor = Color.Gray,
            shape = RectangleShape
        )
    }

}