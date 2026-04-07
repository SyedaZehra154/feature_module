package com.example.feature_auth.presentation.ui_state

import com.example.feature_auth.data.local.entity.UserEntity
import com.example.feature_auth.domain.model.User

data class AuthUiState(
    val user: UserEntity? = null,
    val error: String = ""
)