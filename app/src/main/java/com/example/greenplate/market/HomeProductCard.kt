package com.example.greenplate.market

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.greenplate.R


@Composable
fun ProductItem(product: Product) {
    val context = LocalContext.current

    // Ensure HTTPS and optimize URL for Cloudinary/Firebase
    val imageUrl = product.imageUrl?.replace("http://", "https://") ?: ""

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
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
            .height(270.dp),
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
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = product.title,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                modifier = Modifier.padding(start = 8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFA000),
                        modifier = Modifier.size(20.dp)
                    )

                    Text(
                        text = product.rating,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "Distance",
                        tint = Color(0xFF797373),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = product.location,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = "${product.price} LKR",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000),
                )
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0xFF5EB461))
                        .clickable {  },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = "Navigate",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp).padding(4.dp)
                    )
                }
            }
        }
    }
}
