package ru.yangel.auth_feature.presentation.login

import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yangel.auth_data.storage.repository.AuthRepository
import ru.yangel.auth_feature.presentation.login.state.LoginError
import ru.yangel.auth_feature.presentation.login.state.LoginState
import ru.yangel.auth_feature.presentation.login.state.LoginUiState
import ru.yangel.core.customexception.AuthException
import ru.yangel.core.usecase.ValidateEmailUseCase
import ru.yangel.core.validation.ValidateEmailUseCase
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val emailValidateEmailUseCase: ValidateEmailUseCase = ValidateEmailUseCase()

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Initial)
    val loginState = _loginState.asStateFlow()


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is AuthException -> {
                _loginState.value = LoginState.Error(LoginError.NETWORK_ERROR)
            }

            else -> {
                _loginState.value = LoginState.Error(LoginError.INVALID_CREDENTIALS)
            }
        }
    }

    fun loginUser() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _loginState.value = LoginState.Loading
            authRepository.signInWithEmailAndPassword(
                _loginUiState.value.email,
                _loginUiState.value.password
            )
            _loginState.value = LoginState.Success
        }
    }

    private fun isValidationPassed(): Boolean {
        return emailValidateEmailUseCase.execute(_loginUiState.value.email).isSuccessful
    }

    fun onEmailChange(email: String) {
        _loginUiState.value = _loginUiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _loginUiState.value = _loginUiState.value.copy(password = password)
    }


}