package ru.yangel.auth_feature.presentation.registration.state

data class RegistrationUiState(
    val name: String = "",
    val email: String = "arlosaneq@gmail.com",
    val password: String = "3101911Gg12",
    val isEmailValid: Boolean = false,
    val isNameValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isRegistrationButtonEnabled: Boolean = false
)