package ru.yangel.auth_data.storage

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)