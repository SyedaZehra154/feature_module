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
import androidx.compose.ui.tooling.preview.Preview
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

    // Pass necessary data and callbacks to the stateless content
    LoginContent(
        errorMessage = state.error,
        onLoginClick = { email, password ->
            viewModel.login(email, password)
        },
        onNavigateToSignup = onNavigateToSignup
    )

    LaunchedEffect(state.user) {
        if (state.user != null) {
            onLoginSuccess()
        }
    }
}


@Composable
fun LoginContent(
    errorMessage: String,
    onLoginClick: (String, String) -> Unit,
    onNavigateToSignup: () -> Unit
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

            if (errorMessage.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .background(primaryRed.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    Text(
                        text = errorMessage,
                        color = primaryRed,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { onLoginClick(email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primaryRed),
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

            TextButton(onClick = onNavigateToSignup) {
                Row {
                    Text("New here? ", color = Color.Gray)
                    Text("Sign Up", color = primaryRed, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// --- PREVIEWS ---

@Preview(showBackground = true, name = "Default State")
@Composable
fun PreviewLoginDefault() {
    MaterialTheme {
        LoginContent(
            errorMessage = "",
            onLoginClick = { _, _ -> },
            onNavigateToSignup = {}
        )
    }
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun PreviewLoginError() {
    MaterialTheme {
        LoginContent(
            errorMessage = "Invalid email or password",
            onLoginClick = { _, _ -> },
            onNavigateToSignup = {}
        )
    }
}

//User types → UI state updates (email/password)
//        ↓
//Clicks login
//        ↓
//ViewModel.login()
//        ↓
//Updates StateFlow
//        ↓
//collectAsState() observes
//        ↓
//UI recomposes
//        ↓
//If success → LaunchedEffect triggers navigation



//StateFlow → “What is the current state?”
//SharedFlow → “Something just happened!”
//LiveData → “Old way to observe data”

//LaunchedEffect is used to run coroutine-based side effects
// tied to a composable’s lifecycle.
//Side effects are operations that go beyond UI rendering
// and interact with the outside world.

//State is used to hold data so that when it changes,
// the UI automatically updates (recomposes) in Jetpack Compose