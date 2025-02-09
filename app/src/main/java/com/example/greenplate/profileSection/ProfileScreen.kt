package com.example.greenplate.profileSection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
            Spacer(modifier = Modifier.height(16.dp))
            Header()
            Spacer(modifier = Modifier.height(20.dp))
            Info()
            Spacer(modifier = Modifier.height(8.dp))
            MenuItems()
            Spacer(modifier = Modifier.weight(1f))
            Logout()
        }
    }
}

