package com.example.feature_auth.presentation.ui_state

import com.example.feature_auth.domain.model.User

data class AuthUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)