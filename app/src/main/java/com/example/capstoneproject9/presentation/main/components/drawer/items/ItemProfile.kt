package com.example.capstoneproject9.presentation.main.components.drawer.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.capstoneproject9.R
import com.example.capstoneproject9.components.ShortDivider
import com.example.capstoneproject9.domain.model.User
import com.example.capstoneproject9.presentation.main.components.drawer.ProfileInfo

@Composable
fun ItemProfile(
    user: User,
    navigateToEditProfileScreen: () -> Unit
) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(15.dp),
                    contentAlignment = Alignment.Center
                ){
                    AsyncImage(
                        modifier = Modifier
                            .clip(CircleShape)
                            .width(100.dp)
                            .height(100.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(user.photoUrl)
                            .crossfade(true)
                            .build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }


                Text(
                    text = user.displayName,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(15.dp)

                )
                ProfileInfo {
                    Text(
                        text = it.fullAddress.toString(),
                        fontSize = 30.sp,
                        modifier = Modifier
                            .padding(15.dp)

                    )

                    Text(
                        text = it.contactNumber.toString(),
                        fontSize = 30.sp,
                        modifier = Modifier
                            .padding(15.dp)
                    )
                }



                Button(
                    onClick = { navigateToEditProfileScreen() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.accent)
                    )
                ) {
                    Text(text = "Edit profile here")
                }
            }

        }


}