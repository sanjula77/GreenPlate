package com.example.greenplate.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greenplate.WelcomeScreen
import com.example.greenplate.authentication.LoginScreen
import com.example.greenplate.authentication.RegisterScreen
import com.example.greenplate.donationScreen.DonationInputScreen
import com.example.greenplate.donationScreen.Donate
import com.example.greenplate.education.CourseListScreen
import com.example.greenplate.homeScreen.HomeScreen
import com.example.greenplate.market.ProductInputScreen
import com.example.greenplate.market.ProductScreen
import com.example.greenplate.notification.NotificationScreen
import com.example.greenplate.profileSection.ProfileScreen
import com.example.greenplate.profileSection.UpdateProfileScreen
import com.example.greenplate.topAppBar.CreatePostScreen
import com.example.greenplate.topAppBar.SearchScreen
import com.google.firebase.auth.FirebaseAuth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyNav() {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()
    val startDestination = if (auth.currentUser != null) "home" else "welcome"

    NavHost(navController, startDestination = startDestination) {
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
            ProfileScreen(navController, auth.currentUser?.uid ?: "")
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
        composable("donationInput") {
            DonationInputScreen()
        }
        composable("updateProfile") {
            UpdateProfileScreen(navController, auth.currentUser?.uid ?: "")
        }
        composable("inputSection") {
            CreatePostScreen(navController)
        }
    }
}


/*
@RequiresApi(Build.VERSION_CODES.O)
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
        composable("donationInput") {
            DonationInputScreen()
        }
    }
}
*/