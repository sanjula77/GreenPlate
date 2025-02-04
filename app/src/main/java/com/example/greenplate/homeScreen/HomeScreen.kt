package com.example.greenplate.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greenplate.R
import com.example.greenplate.topAppBar.PostInputSection
import com.example.greenplate.topAppBar.TopBar
import com.example.greenplate.bottomBar.BottomNavigationBar
import com.example.greenplate.donationScreen.DonationCard
import com.example.greenplate.donationScreen.FoodDonationCard
import com.example.greenplate.donationScreen.getSampleDonations
import com.example.greenplate.homeScreen.cardSection.FoodCard
import com.example.greenplate.homeScreen.cardSection.ProductCard
import com.example.greenplate.homeScreen.data.getSampleProducts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    val sampleProducts = getSampleProducts()
    val donations = getSampleDonations()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(scrollBehavior = scrollBehavior) },
        bottomBar = { BottomNavigationBar(navController) },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item { PostInputSection() }

            item {
                Row(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Market Place",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.robotoslabextrabold)),
                    )
                    Text(
                        text = "See all",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {  }
                    )
                }
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(start = 4.dp, end = 4.dp)
                ) {

                    items(sampleProducts) { product ->
                        FoodCard(product)
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Donations",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.robotoslabextrabold)),
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        text = "See all",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {  }
                    )
                }
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(start = 4.dp, end = 4.dp)
                ) {
                    items(donations) { donation ->
                        FoodDonationCard(donation)
                    }
                }
            }
        }
    }
}
