package com.example.greenplate.donationScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import com.example.greenplate.bottomBar.BottomNavigationBar
import com.example.greenplate.topAppBar.DrawerContent
import com.example.greenplate.topAppBar.TopBar
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Donate(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val donationList = remember { mutableStateListOf<Donation>() }

    LaunchedEffect(true) {
        getDonationList { donations ->
            donationList.clear()
            donationList.addAll(donations)
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent() } // ✅ Add the drawer content
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopBar(
                    scrollBehavior = scrollBehavior,
                    navController = navController,
                    onOpenDrawer = { scope.launch { drawerState.open() } } // ✅ Open drawer
                )
            },
            bottomBar = { BottomNavigationBar(navController) },
        ) { paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(donationList) { donation ->
                    DonationCard(donation)
                }
            }
        }
    }
}


/*
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Donate(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    val donationList = remember { mutableStateListOf<Donation>() }

    LaunchedEffect(true) {
        getDonationList { donations ->
            donationList.clear()
            donationList.addAll(donations)
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(scrollBehavior = scrollBehavior, navController = navController) },
        bottomBar = { BottomNavigationBar(navController) },

        ) { paddingValues ->

        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(donationList) { donation ->
                DonationCard(donation)
            }
        }
    }
}*/