package com.example.greenplate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun WelcomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4CAF50), // Green color at the top
                        Color(0xFFF1F1F1)  // Light gray color at the bottom
                    )
                )
            ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.foodlogo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 40.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(300.dp))
            // App Title
            Text(
                text = "Zero Hunger",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = Color(0xFF4CAF50),
                modifier = Modifier.padding(top = 20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Tagline
            Text(
                text = "Connecting Farmers & Buyers for a Hunger-Free Future!",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start,
            )

            Spacer(modifier = Modifier.weight(1f)) // Push button to the bottom

            // Get Started Button
            Button(
                onClick = { /* Your action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text(text = "Get Started", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
