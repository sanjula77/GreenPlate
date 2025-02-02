package com.example.greenplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.greenplate.WelcomeScreen
import com.example.greenplate.authentication.LoginScreen
import com.example.greenplate.authentication.RegisterScreen
import com.example.greenplate.homeScreen.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun MyNav() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Welcome) {
        composable<Welcome>{
            WelcomeScreen(navController)
        }
        composable<Login>{
            LoginScreen(navController)
        }
        composable<Register>{
            RegisterScreen(navController)
        }
        composable<Home>{
            HomeScreen()
        }
    }
}

@Serializable
object Welcome

@Serializable
object Login

@Serializable
object Register

@Serializable
object Home