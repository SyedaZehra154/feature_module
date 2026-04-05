package com.example.feature_auth.domain.usecase

import com.example.feature_auth.domain.repo.AuthRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) = repository.signup(email, password)
}