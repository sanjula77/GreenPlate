package com.example.greenplate.authentication

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.greenplate.R
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

    var rememberMeChecked by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(top = 60.dp))
        Spacer(modifier = Modifier.height(80.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("Email", color = colorResource(id = R.color.grayLtr)) },
            leadingIcon = { Icon(imageVector = Icons.Rounded.Email, contentDescription = "Email Icon",  tint = colorResource(id = R.color.grayLtr)) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.greenBtn2),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                cursorColor = Color.Gray.copy(alpha = 0.5f),
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password", color = colorResource(id = R.color.grayLtr)) },
            leadingIcon = { Icon(imageVector = Icons.Rounded.Lock, contentDescription = "Password Icon",  tint = colorResource(id = R.color.grayLtr)) },
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = "Toggle Password Visibility",  tint = colorResource(id = R.color.grayLtr))
                }
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.greenBtn2),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                cursorColor = Color.Gray.copy(alpha = 0.5f)
            ),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )

        if (!loginError.isNullOrEmpty()) {
            Text(text = loginError!!, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            Checkbox(
                checked = rememberMeChecked,
                onCheckedChange = { rememberMeChecked = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(id = R.color.greenBtn2), // Corrected function
                    uncheckedColor = Color.Gray, // Color when unchecked
                    checkmarkColor = Color.White // Color of the checkmark
                )
            )

            Text(
                text = "Remember Me",
                color = colorResource(id = R.color.grayLtr2),
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Forgot Password?",
                color = colorResource(id = R.color.greenBtn2),
                modifier = Modifier.clickable {  },
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                authViewModel.loginUser(email, password,
                    onSuccess = { navController.navigate("home") },
                    onError = { errorMessage -> loginError = errorMessage }
                )
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.greenBtn2)),
        ) {
            Text(text = "Login", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 16.sp)
        }

        Button(
            onClick = { navController.navigate("home") },
        ) {
            Text(text = "Log")
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

        Spacer(modifier = Modifier.height(12.dp))

        SocialButton(
            text = "Continue with Google",
            iconRes = painterResource(id = R.drawable.google)
        )
        Spacer(modifier = Modifier.height(12.dp))
        SocialButton(
            text = "Continue with Facebook",
            iconRes = painterResource(id = R.drawable.facebook)
        )



        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have an account?", color = colorResource(id = R.color.grayLtr2))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Sign Up",
                color = colorResource(id = R.color.greenBtn2),
                modifier = Modifier.clickable { navController.navigate("register") }
            )
        }
    }
}

@Composable
fun SocialButton(text: String, iconRes: Painter) {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
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
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
    }
}
