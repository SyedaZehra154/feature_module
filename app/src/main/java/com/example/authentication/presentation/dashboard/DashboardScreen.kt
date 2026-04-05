//package com.example.authentication.presentation.dashboard
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Card
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import org.koin.androidx.compose.koinViewModel
//
//@Composable
//fun DashboardScreen(
//    onLogoutClick: () -> Unit
//) {
//    val viewModel: DashboardViewModel = koinViewModel()
//    val state by viewModel.state.collectAsState()
//
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
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            Spacer(modifier = Modifier.height(40.dp))
//
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .shadow(8.dp, RoundedCornerShape(24.dp)),
//                shape = RoundedCornerShape(24.dp)
//            ) {
//                Column(
//                    modifier = Modifier
//                        .background(blueGradient)
//                        .padding(24.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = "Welcome,",
//                        fontSize = 22.sp,
//                        color = Color.White
//                    )
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    Text(
//                        text = state.name,
//                        fontSize = 28.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(32.dp))
//
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .shadow(4.dp, RoundedCornerShape(16.dp)),
//                shape = RoundedCornerShape(16.dp)
//            ) {
//                Column(
//                    modifier = Modifier
//                        .background(Color.White)
//                        .padding(16.dp)
//                ) {
//                    Text(
//                        text = "Email",
//                        fontSize = 14.sp,
//                        color = Color.Gray
//                    )
//                    Text(
//                        text = state.email,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = Color.Black
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.weight(1f))
//            Button(
//                onClick = onLogoutClick,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(56.dp)
//                    .shadow(4.dp, RoundedCornerShape(16.dp)),
//                shape = RoundedCornerShape(16.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
//                contentPadding = PaddingValues()
//            ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(blueGradient)
//                        .padding(horizontal = 16.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "Logout",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.White
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//        }
//    }
//}