package ru.yangel.auth_feature.presentation.login.state

sealed class LoginState {
    data object Initial : LoginState()
    data object Loading : LoginState()
    data object Success : LoginState()
    data class Error(val error: LoginError) : LoginState()
}