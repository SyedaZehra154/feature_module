package com.example.feature_meals.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.feature_meals.domain.model.Meal
import com.example.feature_meals.presentation.uistate.MealUiState
import com.example.feature_meals.presentation.viewmodel.MealViewModel

@Composable
fun MealListScreen(
    onMealClick: (String) -> Unit,
    viewModel: MealViewModel = hiltViewModel()
) {
    // Collecting state from ViewModel
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Trigger load only once when entering the screen
    LaunchedEffect(Unit) {
        viewModel.load()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F8F8) // Your specific background color
    ) {
        when (val s = state) {
            is MealUiState.Loading -> {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFFF44336))
                }
            }
            is MealUiState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        HeaderSection(s.meals.size)
                    }

                    items(s.meals, key = { it.id }) { meal ->
                        FoodCard(
                            meal = meal,
                            onClick = { onMealClick(meal.id) }
                        )
                    }
                }
            }
            is MealUiState.Error -> {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    Text(text = s.message, color = MaterialTheme.colorScheme.error)
                }
            }
            else -> Unit
        }
    }
}

@Composable
fun FoodCard(
    meal: Meal,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() } // Kept your navigation logic
    ) {
        Column {
            Box {
                AsyncImage(
                    model = meal.thumbnailUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )

                // Red Favorite Button
                IconButton(
                    onClick = { /* Internal card logic like liking can go here */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(Color(0xFFF44336), CircleShape)
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                // You can update this string to meal.category or similar if your domain model has it
                Text(
                    text = "Delicious meal prepared fresh",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun HeaderSection(count: Int) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(
            text = "Fast",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
        )
        Text(
            text = "Food",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFF44336)
            )
        )
        Text(
            text = "$count types of items available",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}