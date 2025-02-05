package com.example.greenplate.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

val items = listOf(
    BottomNavItem(
        title = "Home",
        icon = Icons.Rounded.Home,
        route = "home"
    ),
    BottomNavItem(
        title = "Wallet",
        icon = Icons.Rounded.Wallet,
        route = "donate"
    ),
    BottomNavItem(
        title = "Notificatio",
        icon = Icons.Rounded.Notifications,
        route = "notification"
    ),
    BottomNavItem(
        title = "Account",
        icon = Icons.Rounded.AccountCircle,
        route = "account"
    ),
)

@Composable
fun BottomNavigationBar(navController: NavController) {
    // Get current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route, // Highlight the selected icon
                onClick = {
                    if (currentRoute != item.route) { // Prevent reloading the same screen
                        navController.navigate(item.route) {
                            popUpTo("home") { inclusive = false } // Keep the back stack clean
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}