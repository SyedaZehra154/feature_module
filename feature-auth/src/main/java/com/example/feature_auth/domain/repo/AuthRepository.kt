package com.example.feature_auth.domain.repo

import com.example.feature_auth.data.local.entity.UserEntity

interface AuthRepository {
    suspend fun login(email: String, password: String): UserEntity?
    suspend fun signup(email: String, password: String): Long
}