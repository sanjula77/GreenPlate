package com.example.greenplate.market

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun getProductList(callback: (List<Product>) -> Unit) {
    val db = Firebase.firestore

    db.collection("products").get()
        .addOnSuccessListener { documents ->
            val products = documents.mapNotNull { doc ->
                try {
                    doc.toObject(Product::class.java).copy(id = doc.id)
                } catch (e: Exception) {
                    Log.e("Firestore", "Error parsing product: ${doc.id}", e)
                    null  // Skip faulty products
                }
            }
            callback(products)
        }
        .addOnFailureListener { e ->
            Log.e("Firestore", "Error fetching products", e)
            callback(emptyList()) // Return an empty list on failure
        }
}
