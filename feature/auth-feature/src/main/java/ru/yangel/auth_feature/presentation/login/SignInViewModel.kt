package ru.yangel.auth_feature.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.yangel.auth_data.storage.repository.AuthRepository
import ru.yangel.auth_feature.presentation.login.state.LoginState
import ru.yangel.core.usecase.ValidateEmailUseCase
import ru.yangel.core.validation.ValidateEmailUseCase
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val emailValidateEmailUseCase: ValidateEmailUseCase = ValidateEmailUseCase()

    private val _signInState = MutableStateFlow(LoginState())
    val signInState = _signInState.asStateFlow()

    fun loginUser() {
        val emailValid = emailValidateEmailUseCase.execute(_signInState.value.email)
        when {
            emailValid.isSuccessful -> {
                _signInState.value = _signInState.value.copy(isEmailValid = true)
            }

            else -> {
                _signInState.value = _signInState.value.copy(isEmailValid = false)
            }
        }
        authRepository.loginUser()
    }

    fun onEmailChange(email: String) {
        _signInState.value = _signInState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _signInState.value = _signInState.value.copy(password = password)
    }


}