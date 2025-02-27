package com.example.greenplate.testFireStore

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun DisplayDataScreen() {
    val db = Firebase.firestore
    var userList by remember { mutableStateOf<List<UserData>>(emptyList()) }

    LaunchedEffect(Unit) {
        db.collection("test").get()
            .addOnSuccessListener { result ->
                val users = result.documents.mapNotNull { doc ->
                    val name = doc.getString("name")
                    val number = doc.getString("number")
                    val imageUrl = doc.getString("imageUrl")?.replace("http://", "https://") // Force HTTPS

                    Log.d("Firestore", "Fetched Image URL: $imageUrl") // Debugging

                    if (!imageUrl.isNullOrBlank()) {
                        UserData(name.orEmpty(), number.orEmpty(), imageUrl)
                    } else null
                }
                userList = users
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error fetching documents", e)
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(text = "Stored Data", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(userList) { user ->
                UserItem(user)
            }
        }
    }
}

@Composable
fun UserItem(user: UserData) {
    val context = LocalContext.current

    // Generate the optimized image URL
    val optimizedUrl = user.imageUrl.replace("/upload/", "/upload/w_200,h_200,c_fill/")

    // Use the optimized URL in the image request
    val imageRequest = remember(optimizedUrl) {
        ImageRequest.Builder(context)
            .data(optimizedUrl)  // Use optimized URL
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .build()
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageRequest,
                contentDescription = "User Image",
                modifier = Modifier
                    .size(80.dp)
                    .background(MaterialTheme.colorScheme.surface)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = "Name: ${user.name}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Number: ${user.number}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

data class UserData(val name: String, val number: String, val imageUrl: String)
