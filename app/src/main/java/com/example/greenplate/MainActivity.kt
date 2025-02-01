package com.example.greenplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.greenplate.authentication.Login
import com.example.greenplate.authentication.RegisterScreen
import com.example.greenplate.ui.theme.GreenPlateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreenPlateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                   // Home()
                    WelcomeScreen()
                   // Login()
                   /// RegisterScreen()
                }
            }
        }
    }
}
