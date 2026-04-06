package com.example.feature_auth.data.mapper

import com.example.core_common.model.FavouriteMeal
import com.example.feature_auth.data.local.entity.FavouriteMealEntity


//extension func
fun FavouriteMeal.toEntity() = FavouriteMealEntity(
    id           = id,
    name         = name,
    thumbnailUrl = thumbnailUrl
)

fun FavouriteMealEntity.toDomain() = FavouriteMeal(
    id           = id,
    name         = name,
    thumbnailUrl = thumbnailUrl
)