package com.example.greenplate.profileSection

import android.util.Log
import androidx.compose.runtime.*
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun getUserData(userId: String?): User? {
    var user by remember { mutableStateOf<User?>(null) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(userId) {
        if (userId.isNullOrEmpty()) {
            Log.e("Firestore", "Invalid userId: $userId")
            loading = false
            return@LaunchedEffect
        }

        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    user = document.toObject(User::class.java)
                } else {
                    Log.e("Firestore", "No such document for userId: $userId")
                }
                loading = false
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting user data", exception)
                loading = false
            }
    }

    if (loading) {
        Log.d("Firestore", "Loading user data...")
        return null
    }

    return user
}


/*
package com.example.greenplate.profileSection

import android.util.Log
import androidx.compose.runtime.*
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun getUserData(userId: String): User? {
    var user by remember { mutableStateOf<User?>(null) }

    LaunchedEffect(userId) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    user = document.toObject(User::class.java)
                } else {
                    Log.e("Firestore", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting user data", exception)
            }
    }
    return user
}
*/