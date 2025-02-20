package com.example.greenplate.authentication

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
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

                        val userDocRef = db.collection("users").document(userId)

                        // Check if user already exists
                        userDocRef.get().addOnSuccessListener { document ->
                            if (!document.exists()) {
                                userDocRef.set(userMap)
                                    .addOnSuccessListener {
                                        auth.currentUser?.sendEmailVerification()
                                            ?.addOnSuccessListener {
                                                Toast.makeText(
                                                    context,
                                                    "User registered successfully! Please verify your email.",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                                onSuccess()
                                            }
                                            ?.addOnFailureListener { e ->
                                                onError("Error sending verification email: ${e.localizedMessage}")
                                            }
                                    }
                                    .addOnFailureListener { e ->
                                        onError("Error saving user data: ${e.localizedMessage}")
                                    }
                            } else {
                                onSuccess()
                            }
                        }
                    }
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthUserCollisionException -> "This email is already registered."
                        is FirebaseAuthWeakPasswordException -> "Password is too weak. Use at least 6 characters."
                        else -> "Registration failed: ${task.exception?.localizedMessage}"
                    }
                    onError(errorMessage)
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
                    val user = auth.currentUser
                    if (user != null && user.isEmailVerified) {
                        onSuccess() // Successful login
                    } else {
                        onError("Please verify your email before logging in.")
                    }
                } else {
                    val errorMessage = when (task.exception) {
                        is FirebaseAuthInvalidUserException -> "No account found with this email."
                        is FirebaseAuthInvalidCredentialsException -> "Incorrect email or password."
                        else -> "Login failed: ${task.exception?.localizedMessage}"
                    }
                    onError(errorMessage)
                }
            }
    }

    // Logout User
    fun logoutUser() {
        auth.signOut()
    }

    // Check if User is Logged In
    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}



/*package com.example.greenplate.authentication

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
*/