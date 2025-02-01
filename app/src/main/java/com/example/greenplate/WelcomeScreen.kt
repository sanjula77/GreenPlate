package com.example.greenplate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun WelcomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorResource(id = R.color.green_700),
                        colorResource(id = R.color.green_200)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.fooddonation),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(250.dp)
                    .padding(top = 60.dp)
            )

            Spacer(modifier = Modifier.height(180.dp))

            Box(modifier = Modifier.fillMaxWidth().padding(top = 100.dp)) {
                Text(
                    text = "Zero Hunger",
                    fontFamily = FontFamily(Font(R.font.robotoslabextrabold)),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Bridging farmers and buyers to reduce food waste, and sustainable, hunger-free future.",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .border(2.dp, Color.White, shape = RoundedCornerShape(45.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.black))
            ) {
                Text(
                    text = "Get Started",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

        }
    }
}
