package com.example.greenplate.notification

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.navigation.NavController
import com.example.greenplate.bottomBar.BottomNavigationBar

@Composable
fun NotificationScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
    ) { paddingValues ->
        val notifications = sampleNotifications()
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                NotificationHeader()
            }
            items(notifications) { donation ->
                NotificationCard(donation)
            }
        }
    }
}


