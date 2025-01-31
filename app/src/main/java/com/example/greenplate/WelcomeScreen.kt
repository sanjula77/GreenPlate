package com.example.greenplate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg1),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {

            Image(
                painter = painterResource(id = R.drawable.foodlogo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 40.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(250.dp))


            Text(
                text = "Zero Hunger",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = Color.White,
                modifier = Modifier.padding(top = 20.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Connecting Farmers & Buyers for a Hunger-Free Future!",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Start,
            )
            Spacer(modifier = Modifier.weight(1f)) // Push button to the bottom

            Button(
                onClick = { /* Your action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.green_700))
            ) {
                Text(text = "Get Started", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}
