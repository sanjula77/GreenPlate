import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.greenplate.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun getProductList(callback: (List<Product>) -> Unit) {
    val db = Firebase.firestore

    db.collection("products").get()
        .addOnSuccessListener { documents ->
            val products = documents.mapNotNull { doc ->
                val title = doc.getString("title") ?: "No Title"
                val price = doc.getString("price") ?: "0"
                val location = doc.getString("location") ?: "Unknown"
                val rating = doc.getString("rating") ?: "0.0"
                val imageUrl = doc.getString("imageUrl")?.replace("http://", "https://")

                if (!imageUrl.isNullOrBlank()) {
                    Product(doc.id, title, price, location, rating, imageUrl)
                } else null
            }
            callback(products) // Pass the fetched products back
        }
        .addOnFailureListener { e ->
            Log.e("Firestore", "Error fetching products", e)
            callback(emptyList()) // Return an empty list if there's an error
        }
}


@Composable
fun ProductItem(product: Product) {
    val context = LocalContext.current

    val optimizedUrl = product.imageUrl.replace("/upload/", "/upload/w_200,h_200,c_fill/")

    val imageRequest = remember(optimizedUrl) {
        ImageRequest.Builder(context)
            .data(optimizedUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .build()
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(8.dp)
            .width(200.dp)
            .height(270.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column {
            AsyncImage(
                model = imageRequest,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = product.title,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                modifier = Modifier.padding(start = 8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFA000),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = product.rating,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "Distance",
                        tint = Color(0xFF797373),
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = product.location,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = "${product.price} LKR",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000),
                )
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0xFF5EB461))
                        .clickable {  },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = "Navigate",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp).padding(4.dp)
                    )
                }
            }
        }
    }
}


data class Product(
    val id: String,
    val title: String,
    val price: String,
    val location: String,
    val rating: String,
    val imageUrl: String
)
