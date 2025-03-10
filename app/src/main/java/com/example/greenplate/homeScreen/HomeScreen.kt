package com.example.greenplate.homeScreen

import com.example.greenplate.market.Product
import com.example.greenplate.market.ProductItem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.example.greenplate.donationScreen.Donation
import com.example.greenplate.donationScreen.FoodDonationCard
import com.example.greenplate.donationScreen.getDonationList
import com.example.greenplate.market.getProductList
import com.example.greenplate.topAppBar.DrawerContent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val productList = remember { mutableStateListOf<Product>() }
    LaunchedEffect(Unit) {
        getProductList { products ->
            productList.clear()
            productList.addAll(products)
        }
    }

    val donationList = remember { mutableStateListOf<Donation>() }
    LaunchedEffect(Unit) {
        getDonationList { donations ->
            donationList.clear()
            donationList.addAll(donations)
        }
    }

    val auth = FirebaseAuth.getInstance()

    // 🔹 Wrapping Scaffold inside ModalNavigationDrawer
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent() }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    scrollBehavior = scrollBehavior,
                    navController = navController,
                    onOpenDrawer = { scope.launch { drawerState.open() } }
                )
            },
            bottomBar = { BottomNavigationBar(navController) }
        ){ paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    PostInputSection(navController, auth.currentUser?.uid ?: "")
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(
                            text = "Hello changemaker",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = FontFamily(Font(R.font.latobold)),
                            modifier = Modifier.padding(start = 16.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "With just $0.80, you can share the meal with someone in need.",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.grayLtr2),
                            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Market Place",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.interbold)),
                        )
                        Text(
                            text = "View All",
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.greenBtn2),
                            modifier = Modifier.clickable {
                                navController.navigate("product")
                            }
                        )
                    }
                }

                item {
                    if (productList.isNotEmpty()) {
                        LazyRow {
                            items(productList) { product ->
                                ProductItem(product)
                            }
                        }
                    } else {
                        Text(
                            text = "No products available",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                item {
                    Text(
                        text = "Emergency help",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.interbold)),
                        modifier = Modifier.padding(16.dp)
                    )
                    // Ensure EmergencyCard() is implemented
                    EmergencyCard()
                }

                item {
                    Row(
                        modifier = Modifier
                            .padding(start = 8.dp, end = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Donations",
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.interbold)),
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = "View All",
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.greenBtn2),
                            modifier = Modifier.clickable {
                                navController.navigate("donate")
                            }
                        )
                    }
                }

                item {
                    if (donationList.isNotEmpty()) {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth().padding(start = 4.dp, end = 4.dp)
                        ) {
                            items(donationList) { donation ->
                                FoodDonationCard(donation)
                            }
                        }
                    } else {
                        Text(
                            text = "No donations yet",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                item {
                    // Ensure SwipeableCardView() is implemented
                    SwipeableCardView()
                }
            }
        }
    }
}


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    //val products = fetchProducts()

    val productList = remember { mutableStateListOf<Product>() }

    LaunchedEffect(Unit) {
        getProductList { products ->
            productList.clear()
            productList.addAll(products)
        }
    }

    val donationList = remember { mutableStateListOf<Donation>() }

    LaunchedEffect(true) {
        getDonationList { donations ->
            donationList.clear()
            donationList.addAll(donations)
        }
    }

    val auth = FirebaseAuth.getInstance()



    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(scrollBehavior = scrollBehavior, navController = navController) },
        bottomBar = { BottomNavigationBar(navController) },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                PostInputSection(navController, auth.currentUser?.uid ?: "")
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "Hello changemaker",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.latobold)),
                        modifier = Modifier.padding(start = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "With just $0.80, you can share the meal with someone in need.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.grayLtr2),
                        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Market Place",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.interbold)),
                    )
                    Button(
                        onClick = {
                            navController.navigate("donationInput")
                        }
                    ) {
                        Text(
                            text = "Donation input"
                        )
                    }
                    Text(
                        text = "View All",
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.greenBtn2),
                        modifier = Modifier.clickable {
                            navController.navigate("product")
                        }
                    )
                }
            }

            item {
                LazyRow {
                    items(productList) { product ->
                        ProductItem(product)
                    }
                }
            }

          /*  item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(start = 4.dp, end = 4.dp)
                ) {
                    items(products) { product ->
                     //   FoodCard(product)
                    }
                }
            } */

            item {
                Text(
                    text = "Emergency help",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.interbold)),
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 4.dp)
                )
                EmergencyCard()
            }

            item {
                Row(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Donations",
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.interbold)),
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "View All",
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.greenBtn2),
                        modifier = Modifier.clickable {
                            navController.navigate("donate")
                        }
                    )
                }
            }

            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(start = 4.dp, end = 4.dp)
                ) {
                    items(donationList) { donation ->
                        FoodDonationCard(donation)
                    }
                }
            }
            item {
                SwipeableCardView()
            }
        }
    }
}
*/