//package com.example.authentication.domain.repository
//
//import com.example.authentication.domain.model.Resource
//import com.example.authentication.domain.model.User
//import kotlinx.coroutines.flow.Flow
//
//interface UserRepository {
//    suspend fun signUp(
//        email:String,
//        password:String,
//        name:String,
//    ): Resource<Unit>
//
//    suspend fun login (
//        email: String,
//        password: String
//    ): Resource<Unit>
//
//     fun getUser(): Flow<User?>
//}