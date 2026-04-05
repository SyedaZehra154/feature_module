package com.example.feature_auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_auth.domain.usecase.LoginUseCase
import com.example.feature_auth.domain.usecase.SignupUseCase
import com.example.feature_auth.data.local.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val user: UserEntity? = null,
    val error: String = ""
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signupUseCase: SignupUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AuthUiState())
    val state: StateFlow<AuthUiState> = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val user = loginUseCase(email, password)
                if (user != null) {
                    _state.value = AuthUiState(user = user)
                } else {
                    _state.value = AuthUiState(error = "Invalid credentials")
                }
            } catch (e: Exception) {
                _state.value = AuthUiState(error = e.message ?: "Unknown error")
            }
        }
    }

    fun signup(email: String, password: String) {
        viewModelScope.launch {
            try {
                signupUseCase(email, password)
                val user = loginUseCase(email, password)
                _state.value = AuthUiState(user = user)
            } catch (e: Exception) {
                _state.value = AuthUiState(error = e.message ?: "Unknown error")
            }
        }
    }
}