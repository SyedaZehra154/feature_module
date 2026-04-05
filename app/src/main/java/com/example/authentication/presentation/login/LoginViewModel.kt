//package com.example.authentication.presentation.login
//
//import android.util.Log
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.authentication.domain.model.Resource
//import com.example.authentication.domain.repository.UserRepository
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//
//class LoginViewModel(
//    private val userRepository: UserRepository
//): ViewModel(){
//
//    private val TAG = "LoginViewModel"
//
//    var state = MutableStateFlow(LoginState())
//        private set
//
//    fun onIntent(intent: LoginIntent){
//        when(intent){
//
//            is LoginIntent.EmailChanged -> {
//                state.update { it.copy(email = intent.email) }
//            }
//
//            is LoginIntent.PasswordChanged -> {
//                state.update { it.copy(password = intent.password) }
//            }
//
//            is LoginIntent.LoginClicked -> {
//                Log.d(TAG, "Login button clicked")
//                login()
//            }
//        }
//    }
//
//    private fun login(){
//        viewModelScope.launch {
//            state.update { it.copy(isLoading = true) }
//            Log.d(TAG, "Calling userRepository.login for ${state.value.email}")
//            val result = userRepository.login(
//                state.value.email,
//                state.value.password
//            )
//            when(result){
//                is Resource.Success -> {
//                    Log.d(TAG, "Login successful")
//                    state.update {
//                        it.copy(
//                            isLoading = false,
//                            isSuccess = true
//                        )
//                    }
//                }
//                is Resource.Error -> {
//                    Log.e(TAG, "Login error: ${result.message}")
//                    state.update {
//                        it.copy(
//                            isLoading = false,
//                            error = result.message
//                        )
//                    }
//                }
//            }
//        }
//    }
//}