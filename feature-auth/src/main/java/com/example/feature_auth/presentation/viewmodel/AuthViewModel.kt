package com.example.feature_auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_auth.domain.usecase.LoginUseCase
import com.example.feature_auth.domain.usecase.SignupUseCase
import com.example.feature_auth.data.local.entity.UserEntity
import com.example.feature_auth.presentation.ui_state.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



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

// AuthViewModel authentication logic handle karta hai using Clean Architecture.
// Hilt ke through UseCases inject hote hain.
// Login aur signup functions coroutines mein run hote hain aur UseCases ko call karte hain.
// Result ke base par AuthUiState update hoti hai using StateFlow,
// jo UI observe karti hai aur automatically update ho jati hai.



//StateFlow ViewModel mein hota hai jo data ko continuously update karta hai,
// jab ke State Compose UI mein hota hai jo screen ko redraw karta hai jab data change hota hai.

//viewModelScope coroutines ko ViewModel lifecycle ke sath bind karta hai isliye yeh configuration
// changes survive karta hai, jab ke lifecycleScope Activity/Fragment ke sath tied hota hai
// aur rotation par destroy ho jata hai. AuthUiState UI ka state represent
// karta hai jisme user aur error fields hoti hain. ViewModel is state ko update karta
// hai aur UI StateFlow ke through automatically react karti hai

//Resource data layer ka generic wrapper hai jo API ya repository ka result represent karta hai.
// Lekin UiState presentation layer ke liye hota hai jo UI ke different states ko handle karta hai.
// ViewModel Resource ko UiState mein convert karta hai taake UI easily aur flexibly render ho sake