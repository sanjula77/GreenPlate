package com.example.greenplate.homeScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.greenplate.topAppBar.PostInputSection
import com.example.greenplate.topAppBar.TopBar
import com.example.greenplate.bottomBar.BottomNavigationBar
import com.example.greenplate.homeScreen.cardSection.ProductCard
import com.example.greenplate.homeScreen.data.getSampleProducts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    val sampleProducts = getSampleProducts()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(scrollBehavior = scrollBehavior) },
        bottomBar = { BottomNavigationBar() },

    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item { PostInputSection() }
            items(sampleProducts) { product ->
                ProductCard(product)
            }
        }
    }
}



