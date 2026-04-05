//package com.example.authentication.presentation.signup
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
//class SignupViewModel(
//    private val userRepository: UserRepository
//): ViewModel(){
//
//    private val TAG = "SignupViewModel"
//
//    var state = MutableStateFlow(SignupState())
//    private set
//
//    fun onIntent(intent: SignupIntent){
//        when(intent){
//            is SignupIntent.NameChanged -> {
//                state.update { it.copy(name=intent.name) }
//            }
//            is SignupIntent.EmailChanged -> {
//                state.update { it.copy(email=intent.email) }
//            }
//            is SignupIntent.PasswordChanged -> {
//                state.update { it.copy(password=intent.password) }
//            }
//            SignupIntent.SignupClicked -> {
//                Log.d(TAG, "Signup button clicked")
//                signup()
//            }
//        }
//    }
//
//    private fun signup(){
//        viewModelScope.launch {
//            state.update { it.copy(isLoading=true) }
//            Log.d(TAG, "Calling userRepository.signUp for ${state.value.email}")
//            val result = userRepository.signUp(
//                state.value.email,
//                state.value.password,
//                state.value.name
//            )
//
//            when(result) {
//                is Resource.Success -> {
//                    Log.d(TAG, "Signup successful")
//                    state.update {
//                        it.copy(
//                            isLoading=false,
//                            success=true
//                        )
//                    }
//                }
//                is Resource.Error -> {
//                    Log.e(TAG, "Signup error: ${result.message}")
//                    state.update {
//                        it.copy(
//                            isLoading=false,
//                            error=result.message
//                        )
//                    }
//                }
//            }
//        }
//    }
//}