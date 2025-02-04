package com.example.greenplate.donationScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenplate.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
/*
@Composable
fun DonationCardd(donation: Donationn) {
    Card(
        modifier = Modifier
            .width(350.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp) // ✅ Fix
    ) {
        Column {
            Image(
                painter = painterResource(id = donation.imageRes),
                contentDescription = "Donation Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = donation.title,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontFamily = FontFamily(Font(R.font.aftasansthintegular)),
                    )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = donation.description, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = 0.6f,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF9370DB),
                    trackColor = Color.LightGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Collected ${donation.collectedAmount}", fontWeight = FontWeight.Bold)
                    Text(text = "${donation.daysLeft} days to go", color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { /* Navigate to details */ }) {
                        Text(text = "Read more", color = Color(0xFF9370DB))
                    }
                    Button(
                        onClick = {  },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9370DB)),
                        modifier = Modifier.height(43.dp).width(100.dp).padding(top = 8.dp)
                    ) {
                        Text(text = "Donate", color = Color.White)
                    }
                }
            }
        }
    }
}

// ✅ Fix: Match class name with function parameter
data class Donationn(
    val imageRes: Int,
    val title: String,
    val description: String,
    val collectedAmount: String,
    val daysLeft: Int
)

@Preview(showBackground = true)
@Composable
fun DonationPreview() {
    DonationCardd(
        donation = Donationn(
            imageRes = R.drawable.hunger1, // Replace with an actual drawable resource
            title = "Your Love",
            description = "Share Your Food, Share Your Love",
            collectedAmount = "$150,000",
            daysLeft = 30
        )
    )
}
*/