package com.example.greenplate.homeScreen.cardSection

import androidx.annotation.DrawableRes

data class Product(
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String,
    val price: String,
    val sellerName: String,
    @DrawableRes val sellerImageRes: Int,
    val rating: Float,
    val location: String
)

