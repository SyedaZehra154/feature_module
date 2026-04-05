package com.example.feature_auth.data.mapper

import com.example.feature_auth.data.local.entity.UserEntity
import com.example.feature_auth.domain.model.User

fun UserEntity.toDomain(): User {
    return User(id, email, password)
}

fun User.toEntity(): UserEntity {
    return UserEntity(id, email, password)
}