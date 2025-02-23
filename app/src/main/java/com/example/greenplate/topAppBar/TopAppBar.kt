package com.example.greenplate.topAppBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.greenplate.R
import com.example.greenplate.profileSection.getUserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scrollBehavior: TopAppBarScrollBehavior, navController: NavController) {
    val customFontFamily = FontFamily(
        Font(R.font.aftasansthintegular, FontWeight.Normal)
    )

    TopAppBar(
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f),
        ),
        title = {
            Text(
                text = "GreenPlate",
                fontFamily = customFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        },
        actions = {
            IconButton(onClick = {
                navController.navigate("searchScreen")
            }) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(onClick = { /* TODO: Menu */ }) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = "Menu",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    )
}

@Composable
fun PostInputSection(navController: NavController, userId: String) {
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f))
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = "com.example.greenplate.market.Product Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable {  }
        )

        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.95f))
                .border(1.dp, Color.Black, RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "What's on your mind?",
                color = Color.Black.copy(alpha = 0.9f),
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable {  }
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(
            onClick = {

            },
            modifier = Modifier.size(40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "Add",
                modifier = Modifier.size(30.dp).clickable {
                    navController.navigate("productInput")
                }
            )
        }
    }
}
