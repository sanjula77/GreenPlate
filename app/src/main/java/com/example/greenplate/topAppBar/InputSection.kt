package com.example.greenplate.topAppBar

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.FormatColorFill
import androidx.compose.material.icons.filled.Gif
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greenplate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePostScreen(navController: NavController) {
    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        // Handle selected image URI
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .padding(top = 16.dp)
    ) {
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {  navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = colorResource(id = R.color.grayLtr2)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Create post",
                color = colorResource(id = R.color.grayLtr2),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { /* Handle post action */ },
                enabled = false, // Initially disabled
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("NEXT", color = colorResource(id = R.color.grayLtr2))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Profile & Settings
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.my1), // Replace with actual image
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Gihan Sanjula",
                    color = colorResource(id = R.color.grayLtr2),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
               /* Row {
                    Button(
                        onClick = { /* Change audience */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        Icon(Icons.Default.People, contentDescription = "Friends", tint = Color.White)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Friends", color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /* Add album */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        Text("+ Album", color = Color.White)
                    }
                }*/
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Post Input
        TextField(
            value = "",
            onValueChange = { /* Handle text input */ },
            placeholder = { Text("What's on your mind?", color = Color.Gray) },
            textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Post Options
        Column {
            PostOptionItem(Icons.Default.ProductionQuantityLimits, "Product input") { navController.navigate("productInput") }
            PostOptionItem(Icons.Default.Person, "Donation input") { navController.navigate("donationInput") }
            PostOptionItem(Icons.Default.Mood, "Feeling/activity") { /* Handle activity */ }
            PostOptionItem(Icons.Default.LocationOn, "Check in") { /* Handle location */ }
            PostOptionItem(Icons.Default.Videocam, "Live video") { /* Handle live video */ }
            PostOptionItem(Icons.Default.FormatColorFill, "Background color") { /* Handle background color */ }
            PostOptionItem(Icons.Default.Camera, "Camera") { imagePickerLauncher.launch("image/*") }
            PostOptionItem(Icons.Default.Gif, "GIF") { /* Handle GIF */ }
        }
    }
}

@Composable
fun PostOptionItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = text, tint = colorResource(id = R.color.grayLtr2), modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text, color = colorResource(id = R.color.grayLtr2), fontSize = 16.sp)
    }
}
