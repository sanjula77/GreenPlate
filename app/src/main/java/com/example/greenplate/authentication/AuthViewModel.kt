package com.example.greenplate.authentication

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // Register User
    fun registerUser(
        context: Context,
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        agreeTerms: Boolean,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            onError("Please fill in all fields")
            return
        }
        if (!agreeTerms) {
            onError("You must agree to the terms and conditions")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        val userMap = hashMapOf(
                            "firstName" to firstName,
                            "lastName" to lastName,
                            "email" to email
                        )

                        db.collection("users").document(userId).set(userMap)
                            .addOnSuccessListener {
                                Toast.makeText(context, "User created successfully!", Toast.LENGTH_SHORT).show()
                                onSuccess()
                            }
                            .addOnFailureListener { e ->
                                onError("Error saving user data: ${e.message}")
                            }
                    }
                } else {
                    onError("Registration failed: ${task.exception?.message}")
                }
            }
    }

    // Login User
    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (email.isEmpty() || password.isEmpty()) {
            onError("Please fill in all fields")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess() // Successful login
                } else {
                    onError("Invalid email or password")
                }
            }
    }
}
