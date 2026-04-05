package com.example.feature_meals.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.feature_meals.domain.model.Meal

@Composable
fun MealDetailScreen(
    mealId: String?,
    onBack: () -> Unit,
    viewModel: MealDetailViewModel = hiltViewModel()
) {
    // 1. Fetch data when screen starts
    LaunchedEffect(mealId) {
        mealId?.let { viewModel.getMealDetails(it) }
    }

    // 2. Observe the UI State
    val state by viewModel.state.collectAsStateWithLifecycle()

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
        when (val s = state) {
            is MealDetailUiState.Loading -> {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFFF44336))
                }
            }
            is MealDetailUiState.Success -> {
                MealDetailContent(meal = s.meal, onBack = onBack)
            }
            is MealDetailUiState.Error -> {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    Text(text = s.message, color = MaterialTheme.colorScheme.error)
                }
            }
            else -> Unit
        }
    }
}

@Composable
fun MealDetailContent(meal: Meal, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Allows reading long instructions
    ) {
        // --- Header Image ---
        Box(modifier = Modifier.fillMaxWidth().height(320.dp)) {
            AsyncImage(
                model = meal.thumbnailUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.White, CircleShape)
                    .size(40.dp)
                    .align(Alignment.TopStart)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        // --- Info Card ---
        Column(
            modifier = Modifier
                .offset(y = (-30).dp) // Overlap effect
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "$12.50", // Static price as API doesn't provide one
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color(0xFFF44336),
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            // Category Badge (if available in your model)
            Text(
                text = "Category: ${meal.category ?: "Main Dish"}",
                style = MaterialTheme.typography.labelLarge,
                color = Color.Gray
            )

            Spacer(Modifier.height(24.dp))

            // --- Instructions Section ---
            Text(
                text = "Instructions",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = meal.instructions ?: "No instructions provided.",
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 24.sp,
                color = Color.DarkGray
            )

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = { /* Add to cart logic */ },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
            ) {
                Text("Add to Cart", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}