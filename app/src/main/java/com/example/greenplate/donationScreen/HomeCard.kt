package com.example.greenplate.donationScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.greenplate.R


@Composable
fun FoodDonationCard(donation: Donation) {

    val context = LocalContext.current

    // Ensure HTTPS and optimize URL for Cloudinary/Firebase
    val imageUrl = donation.imageUrl?.replace("http://", "https://") ?: ""

    // Debugging
    Log.d("ImageURL", "Loading image from: $imageUrl")

    // Coil Image Request with Advanced Optimizations
    val imageRequest = remember(imageUrl) {
        ImageRequest.Builder(context)
            .data(imageUrl)
            .crossfade(300) // Faster crossfade transition
            .diskCachePolicy(CachePolicy.ENABLED) // Cache on disk
            .memoryCachePolicy(CachePolicy.ENABLED) // Cache in memory
            .networkCachePolicy(CachePolicy.ENABLED) // Enable network cache
            .placeholder(R.drawable.placeholder_image) // Preload placeholder
            .error(R.drawable.error_image) // Show fallback if loading fails
            .build()
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(250.dp)
            .height(325.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {

            // Image with Smooth Loading
            AsyncImage(
                model = imageRequest,
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp)), // Smooth rounded corners
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = donation.title,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1
                )

                Text(
                    text = donation.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Read more",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.greenBtn2),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color(0xFF5EB461))
                            .clickable {  },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Donate",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
            }
        }
    }
}
