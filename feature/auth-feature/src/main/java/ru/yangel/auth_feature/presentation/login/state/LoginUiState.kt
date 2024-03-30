package ru.yangel.auth_feature.presentation.login.state

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean? = null,
    val isPasswordValid: Boolean = false,
    val isLoginButtonEnabled: Boolean = false
)
