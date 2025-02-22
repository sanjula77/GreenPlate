package com.example.greenplate.profileSection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.greenplate.R

@Composable
fun Header(navController: NavController, userId: String) {
    val context = LocalContext.current

    val user = getUserData(userId)
    val optimizedUrl = user?.profileImageUrl?.let {
        it.replace("http://", "https://")
            .replace("/upload/", "/upload/w_200,h_200,c_fill/")
    } ?: ""

    val imageRequest = remember(optimizedUrl) {
        ImageRequest.Builder(context)
            .data(optimizedUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .build()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = colorResource(id = R.color.grayLtr2),
                modifier = Modifier
                    .size(24.dp)
                    .clickable {  }
            )
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                tint = colorResource(id = R.color.grayLtr2),
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.navigate("updateProfile")
                    }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = imageRequest,
                contentDescription = "com.example.greenplate.market.Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = user?.let { "${it.firstName} ${it.lastName}" } ?: "Loading...",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Fashion Model",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                Icons.Default.Phone,
                contentDescription = "Phone",
                tint = colorResource(id = R.color.grayLtr2),
                modifier = Modifier
                    .size(18.dp)
                    .clickable {  }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
               text = user?.phoneNumber ?: "Loading...",
                color = colorResource(id = R.color.grayLtr2),
                fontSize = 13.sp
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                Icons.Default.Email,
                contentDescription = "Email",
                tint = colorResource(id = R.color.grayLtr2),
                modifier = Modifier
                    .size(18.dp)
                    .clickable {  }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = user?.email ?: "Loading...",
                color = colorResource(id = R.color.grayLtr2),
                fontSize = 13.sp
            )
        }
    }
}