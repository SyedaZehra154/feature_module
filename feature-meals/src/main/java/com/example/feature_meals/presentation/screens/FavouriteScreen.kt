package com.example.feature_meals.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.example.core_common.model.FavouriteMeal
import com.example.feature_meals.presentation.viewmodel.MealViewModel

@Composable
fun FavouriteScreen(
    onBack: () -> Unit,
    viewModel: MealViewModel = hiltViewModel()
) {
    val favourites by viewModel.favourites.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color    = Color(0xFFF8F8F8)
    ) {
        Column {

            Row(
                modifier          = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    text  = "Favourites",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }

            if (favourites.isEmpty()) {
                Box(
                    Modifier.fillMaxSize(),
                    Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector        = Icons.Filled.Favorite,
                            contentDescription = null,
                            tint               = Color.LightGray,
                            modifier           = Modifier.size(64.dp)
                        )
                        Spacer(Modifier.height(12.dp))
                        Text(
                            text  = "No favourites yet",
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text  = "Tap the heart on any meal to save it",
                            color = Color.LightGray,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            } else {
                LazyColumn(
                    contentPadding      = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(favourites, key = { it.id }) { meal ->
                        FavouriteCard(
                            meal     = meal,
                            onRemove = {
                                viewModel.toggleFavourite(meal, isFavourite = true)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FavouriteCard(
    meal: FavouriteMeal,
    onRemove: () -> Unit
) {
    Card(
        shape     = RoundedCornerShape(20.dp),
        colors    = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier  = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier          = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model              = meal.thumbnailUrl,
                contentDescription = meal.name,
                modifier           = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale       = ContentScale.Crop
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text     = meal.name,
                style    = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick  = onRemove,
                modifier = Modifier
                    .background(Color(0xFFF44336), CircleShape)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector        = Icons.Filled.Favorite,
                    contentDescription = "Remove",
                    tint               = Color.White,
                    modifier           = Modifier.size(18.dp)
                )
            }
        }
    }
}