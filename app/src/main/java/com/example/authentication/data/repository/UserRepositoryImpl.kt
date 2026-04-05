//package com.example.authentication.data.repository
//
//import android.util.Log
//import com.example.authentication.data.local.dao.UserDao
//import com.example.authentication.data.local.entity.UserEntity
//import com.example.authentication.domain.repository.UserRepository
//import com.example.authentication.domain.model.User
//import com.example.authentication.domain.model.Resource
//import com.fusion.twofa.core.Fusion2FA
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.userProfileChangeRequest
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.flow.map
//import kotlinx.coroutines.tasks.await
//
//class UserRepositoryImpl(
//    private val fireBaseAuth: FirebaseAuth,
//    private val userDao: UserDao
//): UserRepository{
//
//    private val tag = "UserRepositoryImpl_Network"
//
//    override suspend fun signUp(email: String, password: String, name: String): Resource<Unit> {
//        return try {
//            Log.d(tag, ">>> SIGNUP REQUEST: Email=$email")
//            val result = fireBaseAuth.createUserWithEmailAndPassword(email,password).await()
//            val user = result.user
//            if (user != null) {
//                Log.d(tag, "<<< SIGNUP SUCCESS: UID=${user.uid}")
//                val profileUpdates = userProfileChangeRequest { displayName = name }
//                user.updateProfile(profileUpdates).await()
//
//                val entity = UserEntity(uid = user.uid, email = email, name = name)
//                userDao.insertUser(entity)
//
//                // Fusion: Registering device during signup
//                try {
//                    Log.d("Fusion_Log", "Calling Fusion2FA.registerDevice for: $email")
//                    Fusion2FA.registerDevice(
//                        onSuccess = {email->
//                            Log.e("Fusion_Log", "Fusion Registration Failed: $email")
//
//                        },
//                        onFailed = { error ->
//                            Log.e("Fusion_Log", "Fusion Registration Failed: $error")
//                        }
//                    )
//                } catch (e: Exception) {
//                    Log.e("Fusion_Log", "Exception in Fusion Registration: ${e.message}")
//                }
//
//                Resource.Success(Unit)
//            } else {
//                Resource.Error("User registration failed")
//            }
//        } catch (e: Exception) {
//            Log.e(tag, "<<< SIGNUP ERROR: ${e.message}")
//            Resource.Error(e.message ?: "Unknown error")
//        }
//    }
//
//
//    override suspend fun login(email: String, password: String): Resource<Unit> {
//        return try {
//            Log.d(tag, ">>> LOGIN REQUEST (Firebase): Email=$email")
//
//            val result = fireBaseAuth.signInWithEmailAndPassword(email,password).await()
//            val user = result.user
//
//            if (user != null) {
//                Log.d(tag, "<<< LOGIN SUCCESS (Firebase): UID=${user.uid}")
//
//                // --- FUSION TRIGGER ---
//                Log.d("Fusion_Log", "Attempting to trigger Fusion 2FA via verifyMessage for: $email")
//                try {
//                    Fusion2FA.verifyDevice(
//                        onSuccess = {email->
//                            Log.e("Fusion_Log", "Fusion Verification Request Failed: $email")
//
//                        },
//                        onFailed = { error ->
//                            Log.e("Fusion_Log", "Fusion Verification Request Failed: $error")
//                        }
//                    )
//                } catch (e: Exception) {
//                    Log.e("Fusion_Log", "Exception calling Fusion2FA.verifyMessage: ${e.message}")
//                }
//
//                val entity = UserEntity(
//                    uid = user.uid,
//                    email = user.email ?: email,
//                    name = user.displayName ?: "User"
//                )
//                userDao.insertUser(entity)
//                Resource.Success(Unit)
//            } else {
//                Resource.Error("Login failed")
//            }
//        } catch (e: Exception) {
//            Log.e(tag, "<<< LOGIN ERROR: ${e.message}")
//            Resource.Error(e.message ?: "Unknown error")
//        }
//    }
//
//    override fun getUser(): Flow<User?> {
//        val currentUser = fireBaseAuth.currentUser ?: return flowOf(null)
//        return userDao.getUser(currentUser.uid).map { list ->
//            val localUser = list.firstOrNull()
//            if (localUser == null) {
//                User(uid = currentUser.uid, email = currentUser.email ?: "", name = currentUser.displayName ?: "User")
//            } else {
//                User(uid = localUser.uid, email = localUser.email, name = localUser.name)
//            }
//        }
//    }
//}