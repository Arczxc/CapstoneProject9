package com.example.capstoneproject9.components.cards

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.capstoneproject9.components.Price
import com.example.capstoneproject9.components.icons.ThumbImage
import com.example.capstoneproject9.core.AppConstants.NO_VALUE
import com.example.capstoneproject9.core.Utils.Companion.getImageUrl
import com.example.capstoneproject9.domain.model.Image.ProductThumbImage
import com.example.capstoneproject9.domain.model.Product

@Composable
@ExperimentalMaterial3Api
fun D3Card(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .width(172.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = onClick
    ) {
        Column {

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .width(128.dp)
                            .height(128.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://firebasestorage.googleapis.com/v0/b/capstone-414d8.appspot.com/o/Unity.png?alt=media&token=2e589ec2-efd9-4613-aa6b-45cb9a4e25e4")
                            .crossfade(true)
                            .build(),
                        contentDescription = null
                    )
                }


        }
    }
}