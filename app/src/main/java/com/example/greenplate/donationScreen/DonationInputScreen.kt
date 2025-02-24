package com.example.greenplate.donationScreen

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.rounded.CameraAlt
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import com.example.greenplate.R
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.concurrent.Executors

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DonationInputScreen() {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf<String?>(null) }
    var capturedImage by remember { mutableStateOf<Bitmap?>(null) }

    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
        capturedImage = null
    }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        capturedImage = bitmap
        imageUri = null // Reset the gallery image
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

    fun saveDonationToFirestore(title: String, description: String, imageUrl: String) {
        val db = Firebase.firestore
        val donationData = hashMapOf(
            "title" to title,
            "description" to description,
            "imageUrl" to imageUrl,
            "timestamp" to System.currentTimeMillis() // ✅ Correct format
        )

        db.collection("donations").add(donationData)
            .addOnSuccessListener {
                Toast.makeText(context, "Donation added successfully!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding donation", e)
            }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color(0xFFFAFAFA), shape = RoundedCornerShape(24.dp))
                .border(1.dp, Color(0xFF12D18E), shape = RoundedCornerShape(24.dp))
                .clickable { pickImageLauncher.launch("image/*") }, // Opens gallery
            contentAlignment = Alignment.Center
        ) {
            when {
                capturedImage != null -> {
                    Image(
                        bitmap = capturedImage!!.asImageBitmap(),
                        contentDescription = "Captured Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(24.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                imageUri != null -> {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(24.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                else -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "Select Image",
                            tint = Color.Gray.copy(alpha = 0.6f),
                            modifier = Modifier.size(48.dp)
                        )
                        Text(
                            text = "Tap to select image",
                            color = Color.Gray.copy(alpha = 0.6f),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Divider(modifier = Modifier.weight(1f), thickness = 1.dp)
            Text(
                text = "or",
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
            Divider(modifier = Modifier.weight(1f), thickness = 1.dp)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE7FAF4), shape = RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.Center

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { cameraLauncher.launch(null) }
            ) {
                Text(
                    text = "Open Camera & Take a picture",
                    color = Color(0xFF12D18E),
                    fontWeight = FontWeight.Bold,

                    style = MaterialTheme.typography.bodyMedium
                )
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Rounded.CameraAlt,
                        contentDescription = "Open Camera",
                        tint = Color(0xFF12D18E)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF12D18E),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                cursorColor = Color.Gray.copy(alpha = 0.5f),
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF12D18E),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                cursorColor = Color.Gray.copy(alpha = 0.5f),
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                val file = when {
                    imageUri != null -> {
                        // Convert Uri to File
                        val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri!!)
                        val tempFile = File(context.cacheDir, "upload_image.jpg")
                        val outputStream = FileOutputStream(tempFile)
                        inputStream?.copyTo(outputStream)
                        tempFile
                    }
                    capturedImage != null -> {
                        // Convert Bitmap to File
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
                        saveDonationToFirestore(title, description, url)
                    }
                }
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.greenBtn2)),
        ) {
            Text("Upload & Save", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(50.dp))

    }
}
