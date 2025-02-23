package com.example.greenplate.profileSection

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import com.example.greenplate.R
import com.google.firebase.firestore.FirebaseFirestore
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.concurrent.Executors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProfileScreen(navController: NavController, userId: String) {
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    var phoneNumber by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf<String?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }

    // Fetch user data when the screen loads
    LaunchedEffect(userId) {
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val user = document.toObject(User::class.java)
                    if (user != null) {
                        firstName = user.firstName
                        lastName = user.lastName
                        email = user.email
                        phoneNumber = user.phoneNumber
                        imageUrl = user.profileImageUrl
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error fetching user data", e)
            }
    }

    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
        capturedImage = null
    }

    val user = getUserData(userId)
    val optimizedUrl = user?.profileImageUrl?.let {
        it.replace("http://", "https://")
            .replace("/upload/", "/upload/w_200,h_200,c_fill/")
    } ?: ""

    val imageRequest = remember(optimizedUrl) {
        ImageRequest.Builder(context)
            .data(optimizedUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .build()
    }

    fun uploadToCloudinary(imageFile: File, onSuccess: (String) -> Unit) {
        val cloudinary = Cloudinary(
            ObjectUtils.asMap(
                "cloud_name", "dtsbbxjse",
                "api_key", "649771285557873",
                "api_secret", "J2TmU7LZtF7HpRjefb_qH7x-4p4"
            )
        )

        Executors.newSingleThreadExecutor().execute {
            try {
                val uploadResult = cloudinary.uploader().upload(imageFile, ObjectUtils.emptyMap())
                val uploadedImageUrl = uploadResult["url"].toString()
                onSuccess(uploadedImageUrl)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateUserProfile(userId: String, imageUrl: String?, phoneNumber: String) {
        val userUpdates = mapOf(
            "profileImageUrl" to (imageUrl ?: ""),
            "phoneNumber" to phoneNumber,
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email
        )

        db.collection("users").document(userId)
            .update(userUpdates)
            .addOnSuccessListener {
                Log.d("Firestore", "Profile updated successfully!")
                Toast.makeText(context, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error updating profile: ${e.message}")
            }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(55.dp))
        // Image Section
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.3f))
                .clickable { pickImageLauncher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            when {
                capturedImage != null -> {
                    Image(
                        bitmap = capturedImage!!.asImageBitmap(),
                        contentDescription = "Captured Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                imageUri != null -> {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Selected Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                imageUrl != null -> {
                    AsyncImage(
                        model = imageRequest,
                        contentDescription = "com.example.greenplate.market.Product Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )
                }
                else -> {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = "Default Image",
                        tint = Color.Gray.copy(alpha = 0.6f),
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(75.dp))

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF12D18E),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                cursorColor = Color.Gray.copy(alpha = 0.5f),
            ),
            textStyle = TextStyle(color = colorResource(id = R.color.grayLtr))
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF12D18E),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                cursorColor = Color.Gray.copy(alpha = 0.5f),
            ),
            textStyle = TextStyle(color = colorResource(id = R.color.grayLtr))
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            readOnly = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF12D18E),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                cursorColor = Color.Gray.copy(alpha = 0.5f),
            ),
            textStyle = TextStyle(color = colorResource(id = R.color.grayLtr))
        )

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF12D18E),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                cursorColor = Color.Gray.copy(alpha = 0.5f),
            ),
            textStyle = TextStyle(color = colorResource(id = R.color.grayLtr))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val file = when {
                    imageUri != null -> {
                        val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri!!)
                        val tempFile = File(context.cacheDir, "upload_image.jpg")
                        val outputStream = FileOutputStream(tempFile)
                        inputStream?.copyTo(outputStream)
                        tempFile
                    }
                    capturedImage != null -> {
                        val tempFile = File(context.cacheDir, "captured_image.jpg")
                        val outputStream = FileOutputStream(tempFile)
                        capturedImage!!.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                        outputStream.flush()
                        outputStream.close()
                        tempFile
                    }
                    else -> null
                }

                file?.let {
                    uploadToCloudinary(it) { url ->
                        imageUrl = url
                        updateUserProfile(userId, url, phoneNumber)
                    }
                } ?: updateUserProfile(userId, imageUrl, phoneNumber)
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF12D18E)),
        ) {
            Text("Update Profile", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 16.sp)
        }
    }
}
