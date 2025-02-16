package com.example.greenplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greenplate.WelcomeScreen
import com.example.greenplate.authentication.LoginScreen
import com.example.greenplate.authentication.RegisterScreen
import com.example.greenplate.donationScreen.Donate
import com.example.greenplate.education.CourseListScreen
import com.example.greenplate.homeScreen.HomeScreen
import com.example.greenplate.market.ProductInputScreen
import com.example.greenplate.market.ProductScreen
import com.example.greenplate.notification.NotificationScreen
import com.example.greenplate.profileSection.ProfileScreen
import com.example.greenplate.topAppBar.SearchScreen

@Composable
fun MyNav() {

    val navController = rememberNavController()
    NavHost(navController, startDestination ="welcome") {

        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("donate") {
            Donate(navController)
        }
        composable("product") {
            ProductScreen(navController)
        }
        composable("account") {
            ProfileScreen(navController)
        }
        composable("notification") {
            NotificationScreen(navController)
        }
        composable("searchScreen") {
            SearchScreen()
        }
        composable("courseList") {
            CourseListScreen(navController)
        }
        composable("productInput") {
            ProductInputScreen()
        }
    }
}
