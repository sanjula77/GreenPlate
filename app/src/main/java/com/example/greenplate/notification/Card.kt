package com.example.greenplate.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotificationCard(donation: Data) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(donation.userImage),
            contentDescription = null,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(
                donation.userName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 19.sp
            )
            Text(
                donation.message,
                color = Color.Gray,
                maxLines = 2,
                fontSize = 13.sp,
                lineHeight = 17.sp
            )
            if (donation.timeAgo.isNotEmpty()) {
                Text(
                    donation.timeAgo,
                    fontSize = 12.sp,
                    color = Color.LightGray,
                    lineHeight = 15.sp
                )
            }
        }
    }
}