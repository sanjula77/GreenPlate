package com.example.greenplate.market

data class Product(
    val id: String = "",  // Default values to prevent null issues
    val title: String = "",
    val description: String = "",
    val price: String = "0",
    val location: String = "Unknown",
    val rating: String = "0.0",
    val imageUrl: String = ""
) {
    constructor() : this("", "", "", "0", "Unknown", "0.0", "") // Empty constructor required by Firestore
}
