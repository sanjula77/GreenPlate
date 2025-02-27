package com.example.greenplate.market

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavController) {

    val productList = remember { mutableStateListOf<Product>() }

    LaunchedEffect(Unit) {
        getProductList { products ->
            productList.clear()
            productList.addAll(products)
        }
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerContent() } // ✅ Ensure DrawerContent exists
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
                items(productList) { product ->
                    ProductCard(product)
                }
            }
        }
    }
}


/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(navController: NavController) {

    val productList = remember { mutableStateListOf<Product>() }

    LaunchedEffect(Unit) {
        getProductList { products ->
            productList.clear()
            productList.addAll(products) // ✅ Avoid unnecessary recomposition
        }
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(scrollBehavior = scrollBehavior, navController = navController) },
        bottomBar = { BottomNavigationBar(navController) },

        ) { paddingValues ->

        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(productList) { product ->
                ProductCard(product)
            }
        }
    }
}
*/
