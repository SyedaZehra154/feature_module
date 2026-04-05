package com.example.feature_auth.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.feature_auth.data.local.entity.UserEntity
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long  // Return Long (rowId)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity?
}