//package com.example.authentication.data.local.dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.authentication.data.local.entity.UserEntity
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface  UserDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user: UserEntity)
//
//    @Query("Select * from usersers where uid = :uid")
//    fun getUser(uid:String):Flow<List<UserEntity>>
//
//    @Query("Select * from users where uid = :uid")
//    suspend fun getUserSync(uid:String): UserEntity?
//
//    @Query("Delete from users")
//    suspend fun cleanUser()
//
//    @Query("SELECT * FROM users ORDER BY rowid DESC LIMIT 1")
//    fun getLastUser(): Flow<UserEntity?>
//}