package com.example.greenplate.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greenplate.R

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMeChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 60.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Email, contentDescription = "Email Icon") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Password Icon") },
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = "Toggle Password Visibility")
                }
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Checkbox(
                checked = rememberMeChecked,
                onCheckedChange = { rememberMeChecked = it }
            )

            Text(
                text = "Remember Me",
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Forgot Password?",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {  },
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate("home")
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = "Login", style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            Divider(modifier = Modifier.weight(1f), thickness = 1.dp)
            Text(
                text = "or",
                modifier = Modifier.padding(horizontal = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Divider(modifier = Modifier.weight(1f), thickness = 1.dp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        SocialButton(
            text = "Continue with Google",
            iconRes = painterResource(id = R.drawable.google),
            backgroundColor = Color(0xFFBEFFAF),
        )

        Spacer(modifier = Modifier.height(12.dp))

        SocialButton(
            text = "Continue with Facebook",
            iconRes = painterResource(id = R.drawable.facebook),
            backgroundColor = Color(0xFFBEFFAF),        )

        Spacer(modifier = Modifier.height(12.dp))

     /*   SocialButton(
            text = "Continue with Apple",
            iconRes = painterResource(id = R.drawable.apple),
            backgroundColor = Color(0xFFE1FFD9),
        )*/
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account?",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable {
                    navController.navigate("register")
                }
            )
        }
    }
}

@Composable
fun SocialButton(text: String, iconRes: Painter, backgroundColor: Color) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(24.dp),
      //  border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Icon(
                painter = iconRes,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}
