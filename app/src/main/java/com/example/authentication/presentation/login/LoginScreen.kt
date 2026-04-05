//package com.example.authentication.presentation.login
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import org.koin.androidx.compose.koinViewModel
//
//@Composable
//fun LoginScreen(
//    onNavigateSignup: () -> Unit,
//    onLoginSuccess: () -> Unit,
//) {
//    val viewModel: LoginViewModel = koinViewModel()
//    val state by viewModel.state.collectAsState()
//
//    LoginScreenContent(
//        state = state,
//        onIntent = viewModel::onIntent,
//        onNavigateSignup = onNavigateSignup
//    )
//
//    LaunchedEffect(state.isSuccess) {
//        if (state.isSuccess) {
//            onLoginSuccess()
//        }
//    }
//}
//
//@Composable
//fun LoginScreenContent(
//    state: LoginState,
//    onIntent: (LoginIntent) -> Unit,
//    onNavigateSignup: () -> Unit
//) {
//    val blueGradient = Brush.horizontalGradient(
//        colors = listOf(Color(0xFF6A11CB), Color(0xFF2575FC))
//    )
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = Color(0xFFF2F2F2)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(24.dp),
//            verticalArrangement = Arrangement.Center
//        ) {
//
//            Text(
//                text = "Welcome Back 👋",
//                fontSize = 34.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF333333)
//            )
//
//            Spacer(Modifier.height(40.dp))
//
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .shadow(8.dp, RoundedCornerShape(24.dp)),
//                shape = RoundedCornerShape(24.dp)
//            ) {
//
//                Column(
//                    modifier = Modifier
//                        .background(Color.White)
//                        .padding(24.dp)
//                ) {
//
//                    OutlinedTextField(
//                        value = state.email,
//                        onValueChange = { onIntent(LoginIntent.EmailChanged(it)) },
//                        label = { Text("Email") },
//                        modifier = Modifier.fillMaxWidth(),
//                        shape = RoundedCornerShape(12.dp)
//                    )
//
//                    Spacer(Modifier.height(16.dp))
//
//                    OutlinedTextField(
//                        value = state.password,
//                        onValueChange = { onIntent(LoginIntent.PasswordChanged(it)) },
//                        label = { Text("Password") },
//                        visualTransformation = PasswordVisualTransformation(),
//                        modifier = Modifier.fillMaxWidth(),
//                        shape = RoundedCornerShape(12.dp)
//                    )
//
//                    Spacer(Modifier.height(24.dp))
//
//                    Button(
//                        onClick = { onIntent(LoginIntent.LoginClicked) },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(56.dp)
//                            .shadow(4.dp, RoundedCornerShape(16.dp)),
//                        shape = RoundedCornerShape(16.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
//                        contentPadding = PaddingValues()
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .background(blueGradient),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            if (state.isLoading)
//                                CircularProgressIndicator(color = Color.White)
//                            else
//                                Text("Login", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
//                        }
//                    }
//
//                    Spacer(Modifier.height(20.dp))
//
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Center,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(
//                            text = "Don't have an account? ",
//                            fontSize = 14.sp,
//                            color = Color.Gray
//                        )
//                        Text(
//                            text = "Sign Up",
//                            fontSize = 14.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = Color(0xFF2575FC),
//                            modifier = Modifier.clickable(
//                                interactionSource = remember { MutableInteractionSource() },
//                                indication = null
//                            ) {
//                                onNavigateSignup()
//                            }
//                        )
//                    }
//
//                    if (state.error != null) {
//                        Spacer(Modifier.height(8.dp))
//                        Text(
//                            text = state.error!!,
//                            color = Color.Red,
//                            fontSize = 12.sp,
//                            modifier = Modifier.align(Alignment.CenterHorizontally)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun LoginPreview() {
//    LoginScreenContent(
//        state = LoginState(email = "test@example.com"),
//        onIntent = {},
//        onNavigateSignup = {}
//    )
//}