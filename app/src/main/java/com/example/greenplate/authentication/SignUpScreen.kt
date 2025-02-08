package com.example.greenplate.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.greenplate.R
@Composable
fun RegisterScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var agreeTerms by remember { mutableStateOf(false) }
    var receiveUpdates by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {  }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = colorResource(id = R.color.greenBtn2)
                )
            }
            Text(
                text = "Create Your Account",
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = {
                Text(
                    "First Name",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text(
                "Last Name",
                color = Color.Gray,
                fontSize = 12.sp
            ) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address",color = Color.Gray,
                fontSize = 12.sp) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password (6+ Characters)", color = Color.Gray,
                fontSize = 12.sp) },
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Toggle Password Visibility",
                        tint = colorResource(id = R.color.greenBtn2),
                    )
                }
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(
                checked = agreeTerms,
                onCheckedChange = { agreeTerms = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(id = R.color.greenBtn2), // Corrected function
                    uncheckedColor = Color.Gray, // Color when unchecked
                    checkmarkColor = Color.White // Color of the checkmark
                )
            )
            Text(
                text = "I agree to ",
                color = colorResource(id = R.color.grayLtr),
                fontSize = 11.sp
            )
            Text(
                text = "Terms & Conditions",
                fontSize = 11.sp,
                color = colorResource(id = R.color.greenBtn2)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = receiveUpdates,
                onCheckedChange = { receiveUpdates = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(id = R.color.greenBtn2), // Corrected function
                    uncheckedColor = Color.Gray, // Color when unchecked
                    checkmarkColor = Color.White // Color of the checkmark
                )
            )
            Text(
                text = "I doesn't like to receive reports & updates on learning opportunities",
                style = TextStyle(lineHeight = 16.sp),
                color = colorResource(id = R.color.grayLtr),
                fontSize = 11.sp
            )
        }

        Spacer(modifier = Modifier.height(80.dp))

        // Create Account Button
        Button(
            onClick = {  },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.greenBtn2)),
            ) {
            Text(
                text = "Create An Account",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Divider(thickness = 1.dp)

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Already have an account?",
                fontWeight = FontWeight.Normal,
                color = colorResource(id = R.color.grayLtr2),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Log in",
                fontSize = 14.sp,
                color = colorResource(id = R.color.greenBtn2),
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )
        }
    }
}
