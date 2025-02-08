package com.example.greenplate.profileSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greenplate.R
import com.example.greenplate.bottomBar.BottomNavigationBar

@Composable
fun ProfileScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
           // TopSection()
            Spacer(modifier = Modifier.height(24.dp))
            HeaderSection()
            Spacer(modifier = Modifier.height(16.dp))
            InfoSection()
            Spacer(modifier = Modifier.height(24.dp))
            MenuItems()
            Spacer(modifier = Modifier.weight(1f))
            Logout()
        }
    }
}

/*
@Composable
fun TopSection() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Settings",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Settings",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
    Divider(
        color = Color.Gray.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}
*/
@Composable
fun HeaderSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.my1),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "Gihan Sanjula",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Fashion Model",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Settings",
            tint = Color.Gray,
            modifier = Modifier
                .size(24.dp)
                .clickable {  }
        )
    }
}

@Composable
fun InfoSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
           // .padding(16.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "$140.00", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "Wallet", color = Color.Gray, fontSize = 14.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "12", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = "Orders", color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun MenuItems() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)  // Add padding around the whole section
    ) {
        val menuItems = listOf(
            "Your Favorites" to Icons.Default.Favorite,
            "Payment" to Icons.Default.Payment,
            "Tell Your Friend" to Icons.Default.Share,
            "Promotions" to Icons.Default.Star,
            "Settings" to Icons.Default.Settings
        )

        menuItems.forEach { (title, icon) ->
            MenuItem(title = title, icon = icon)
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}

@Composable
fun MenuItem(title: String, icon: ImageVector) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click */ }
            .padding(vertical = 12.dp)
    ) {
        Icon(imageVector = icon, contentDescription = title, tint = Color.Gray, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, fontSize = 16.sp, color = Color.Black)
    }

}

@Composable
fun Logout() {
/*
    Divider(
        color = Color.Gray.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = Modifier.padding(vertical = 8.dp)
    )
*/
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Logout,
            contentDescription = "Settings",
            tint = Color.Red,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Log out",
            color = Color.Red,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
