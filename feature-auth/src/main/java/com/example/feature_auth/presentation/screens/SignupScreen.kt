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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.feature_auth.presentation.AuthViewModel

@Composable
fun SignupScreen(
    viewModel: AuthViewModel,
    onSignupSuccess: () -> Unit
) {
    // In a real app, you'd likely collect state from the viewModel here
    SignupContent(
        onSignupClick = { email, password ->
            viewModel.signup(email, password)
            onSignupSuccess()
        },
        onBackToLogin = onSignupSuccess
    )
}

@Composable
fun SignupContent(
    onSignupClick: (String, String) -> Unit,
    onBackToLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val primaryRed = Color(0xFFF44336)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Join Us",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
            )

            Text(
                text = "Create an account to get started",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Gray,
                    letterSpacing = 0.5.sp
                ),
                modifier = Modifier.padding(bottom = 48.dp)
            )

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

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { onSignupClick(email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryRed),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text(
                    text = "CREATE ACCOUNT",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        letterSpacing = 1.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(onClick = onBackToLogin) {
                Row {
                    Text("Already have an account? ", color = Color.Gray)
                    Text(
                        "Login",
                        color = primaryRed,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// --- PREVIEW ---

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun SignupScreenPreview() {
    MaterialTheme {
        SignupContent(
            onSignupClick = { _, _ -> },
            onBackToLogin = {}
        )
    }
}
//allback functions are used to handle actions/events by letting another part of the code decide what to do.