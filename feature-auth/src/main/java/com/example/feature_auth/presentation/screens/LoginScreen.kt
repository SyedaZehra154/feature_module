package com.example.feature_auth.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_auth.presentation.AuthViewModel

@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onNavigateToSignup: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val primaryRed = Color(0xFFF44336) // Your requested color

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Pure solid background for a clean look
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Stronger, Bold Header
            Text(
                text = "Welcome",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            )

            Text(
                text = "Please enter your details",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Gray,
                    letterSpacing = 0.5.sp
                ),
                modifier = Modifier.padding(bottom = 48.dp)
            )

            // High-Contrast Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = primaryRed) },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryRed,
                    focusedLabelColor = primaryRed,
                    cursorColor = primaryRed,
                    unfocusedBorderColor = Color(0xFFE0E0E0)
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // High-Contrast Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = primaryRed) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryRed,
                    focusedLabelColor = primaryRed,
                    cursorColor = primaryRed,
                    unfocusedBorderColor = Color(0xFFE0E0E0)
                ),
                shape = RoundedCornerShape(12.dp)
            )

            // Error message layout improvement
            if (state.error.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .background(primaryRed.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = state.error,
                        color = primaryRed,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Solid Primary Action Button
            Button(
                onClick = { viewModel.login(email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryRed
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text(
                    text = "LOGIN",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        letterSpacing = 1.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Link with weight adjustment
            TextButton(onClick = onNavigateToSignup) {
                Row {
                    Text("New here? ", color = Color.Gray)
                    Text(
                        "Sign Up",
                        color = primaryRed,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    LaunchedEffect(state.user) {
        if (state.user != null) {
            onLoginSuccess()
        }
    }
}