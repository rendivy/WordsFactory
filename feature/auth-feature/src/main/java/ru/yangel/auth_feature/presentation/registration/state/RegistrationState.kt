package ru.yangel.auth_feature.presentation.registration.state

data class RegistrationState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isEmailValid: Boolean = false,
    val isNameValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isRegistrationButtonEnabled: Boolean = false
)