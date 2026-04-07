package com.example.feature_auth.data.repository

import com.example.feature_auth.data.local.dao.UserDao
import com.example.feature_auth.data.local.entity.UserEntity
import com.example.feature_auth.domain.repo.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

//  Singleton: only one instance of this class in the whole app
@Singleton

//  Hilt will inject dependencies automatically
class AuthRepositoryImpl @Inject constructor(

    //  DAO injected (used to access database)
    private val userDao: UserDao

) : AuthRepository { //  Implements AuthRepository interface

    //  Login function
    // Takes email & password and checks in database
    override suspend fun login(email: String, password: String): UserEntity? {

        //  Calls DAO function to find matching user
        return userDao.getUserByEmailAndPassword(email, password)
    }

    //  Signup function
    // Creates new user and saves in database
    override suspend fun signup(email: String, password: String): Long {

        //  Create UserEntity object
        val user = UserEntity(email = email, password = password)

        //  Insert user into DB and return inserted row ID
        return userDao.insertUser(user)
    }
}