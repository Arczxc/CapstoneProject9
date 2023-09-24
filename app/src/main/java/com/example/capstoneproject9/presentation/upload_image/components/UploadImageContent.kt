package com.example.capstoneproject9.presentation.upload_image.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capstoneproject9.components.Hyperlink
import com.example.capstoneproject9.components.Hyperlink2
import com.example.capstoneproject9.components.HyperlinkText
import com.example.capstoneproject9.components.Message
import com.example.capstoneproject9.core.FirebaseConstants.ALL_IMAGES
import com.example.capstoneproject9.core.FirebaseConstants.OPEN_GALLERY
import com.example.capstoneproject9.presentation.upload_image.UploadImageViewModel
import kotlinx.coroutines.*

@ExperimentalMaterial3Api
@Composable
fun UploadImageContent(
    padding: PaddingValues,
    navigateToThankYouScreen: () -> Unit,
    viewModel: UploadImageViewModel = hiltViewModel()
){
    //will add viewmodel instance

    val coroutineScope = rememberCoroutineScope()
    val galleryLauncher =  rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
        imageUri?.let {
            viewModel.addImageToStorage(imageUri)
        }
    }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.BottomCenter
        ) {

            Column(modifier = Modifier.padding(15.dp)) {


                Hyperlink2(fullText = "DOWNLOAD" , linkText = "" )

                HyperlinkText(
                    fullText = "Create your 3D design here",
                    linkText = listOf("design", "here"),
                    hyperlinks = listOf("unitydl://mylink/", "unitydl://mylink/")
                )
                Button(
                    onClick = {
                        GlobalScope.launch(Dispatchers.IO) {
                            galleryLauncher.launch(ALL_IMAGES)
                            delay(10000L)
                            withContext(Dispatchers.Main){
                                navigateToThankYouScreen()
                            }
                        }

                    }
                ) {
                    Text(
                        text = OPEN_GALLERY,
                        fontSize = 18.sp
                    )
                }
            }

        }


    /*if (showingDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showingDialog.value = false
            },
            text = {
                Text(text = "Are you sure you want to delete your order?")
            },
            title = {
                Text(text = "Delete Order")
            },
            confirmButton = {
                Text(
                    text = "Ok",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(
                            onClick = {
                                galleryLauncher.launch(ALL_IMAGES)
                            }
                        )
                )
            },
            dismissButton = {
                Text(
                    text = "cancel",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable(
                            onClick = {
                                showingDialog.value = false
                            }
                        )
                )
            },
            textContentColor = Color.Magenta,
            shape = RectangleShape
        )
    }*/

    AddImageToStorage(
        addImageToDatabase = { downloadUrl ->
            viewModel.addImageToDatabase(downloadUrl)
        }
    )


}