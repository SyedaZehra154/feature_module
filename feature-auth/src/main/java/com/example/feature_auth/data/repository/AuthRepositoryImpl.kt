package com.example.feature_auth.data.repository

import com.example.feature_auth.data.local.dao.UserDao
import com.example.feature_auth.data.local.entity.UserEntity
import com.example.feature_auth.domain.repo.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao  // ⚠ Make sure this is injected properly
) : AuthRepository {

    override suspend fun login(email: String, password: String): UserEntity? {
        // Use the correct Dao method name
        return userDao.getUserByEmailAndPassword(email, password)
    }

    override suspend fun signup(email: String, password: String): Long {
        val user = UserEntity(email = email, password = password)
        return userDao.insertUser(user) // Now returns Long
    }
}