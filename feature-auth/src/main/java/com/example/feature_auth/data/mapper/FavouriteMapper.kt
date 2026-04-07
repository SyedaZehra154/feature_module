package com.example.feature_auth.data.mapper

import com.example.core_common.model.FavouriteMeal
import com.example.feature_auth.data.local.entity.FavouriteMealEntity

//  Extension function: Domain → Database Entity
// Converts FavouriteMeal (domain model) to FavouriteMealEntity (DB model)
fun FavouriteMeal.toEntity() = FavouriteMealEntity(
    id           = id,            //  Copy id
    name         = name,          //  Copy name
    thumbnailUrl = thumbnailUrl   //  Copy thumbnail
)

//  Extension function: Database Entity → Domain
// Converts FavouriteMealEntity (DB model) to FavouriteMeal (domain model)
fun FavouriteMealEntity.toDomain() = FavouriteMeal(
    id           = id,            //  Copy id
    name         = name,          // Copy name
    thumbnailUrl = thumbnailUrl   //  Copy thumbnail
)