package com.example.greenplate.profileSection

data class User(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val profileImageUrl: String = "https://res.cloudinary.com/dtsbbxjse/image/upload/v1740210518/aaaa_vmte7g.jpg",  // Default image
    val phoneNumber: String = "0714858298"
)

