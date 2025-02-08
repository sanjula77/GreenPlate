package com.example.greenplate.donationScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenplate.R


@Composable
fun FoodDonationCard(donation: Donation) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(250.dp)
            .height(325.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = donation.imageRes),
                contentDescription = "Food Donation",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = donation.title,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Text(
                    text = donation.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Read more",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.greenBtn2),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    Box(
                        modifier = Modifier
                            .width(90.dp)
                            .height(40.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(colorResource(id = R.color.greenBtn2))
                            .clickable {  },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Donate",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
            }
        }
    }
}
