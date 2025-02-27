package com.example.greenplate.testFireStore


import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.concurrent.Executors

@Composable
fun TestScreen() {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var imageUrl by remember { mutableStateOf<String?>(null) }

    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }

    fun uploadToCloudinary(imageFile: File, onSuccess: (String) -> Unit) {
        val cloudinary = Cloudinary(
            ObjectUtils.asMap(
            "cloud_name", "dtsbbxjse",
            "api_key", "383597814796756",
            "api_secret", "FQjvLx2CzRqQDExvZ5WU8u2BPe0"
        ))

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

    fun saveDataToFirestore(name: String, number: String, imageUrl: String) {
        val db = Firebase.firestore
        val userData = hashMapOf(
            "name" to name,
            "number" to number,
            "imageUrl" to imageUrl
        )

        db.collection("test").add(userData)
            .addOnSuccessListener { documentReference ->
                Log.d("Firestore", "Document added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error adding document", e)
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = number, onValueChange = { number = it }, label = { Text("Number") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { pickImageLauncher.launch("image/*") }) {
            Text("Pick Image")
        }

        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Selected Image",
                modifier = Modifier.size(100.dp)
            )
        }

        Button(
            onClick = {
                imageUri?.let { uri ->
                    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
                    val file = File(context.cacheDir, "upload_image.jpg")
                    val outputStream = FileOutputStream(file)
                    inputStream?.copyTo(outputStream)
                    uploadToCloudinary(file) { url ->
                        imageUrl = url
                        saveDataToFirestore(name, number, url)
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Upload & Save")
        }

        imageUrl?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Uploaded Image URL: $it")
        }
    }
}
