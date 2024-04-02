package ru.yangel.auth_feature.presentation.registration.state

sealed class RegistrationState {
    data object Initial : RegistrationState()
    data object Loading : RegistrationState()
    data object Success : RegistrationState()
    data class Error(val registrationError: RegistrationError) : RegistrationState()
}