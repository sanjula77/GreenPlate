package com.example.greenplate.donationScreen

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun getDonationList(callback: (List<Donation>) -> Unit) {
    val db = Firebase.firestore

    db.collection("donations").get()
        .addOnSuccessListener { documents ->
            val donations = mutableListOf<Donation>()
            for (doc in documents) {
                try {
                    val donation = doc.toObject(Donation::class.java).copy(id = doc.id)
                    donations.add(donation)
                } catch (e: Exception) {
                    Log.e("Firestore", "Error parsing donation: ${doc.id}", e)
                }
            }
            callback(donations)
        }
        .addOnFailureListener { e ->
            Log.e("Firestore", "Error fetching donations", e)
            callback(emptyList()) // Return an empty list on failure
        }
}

