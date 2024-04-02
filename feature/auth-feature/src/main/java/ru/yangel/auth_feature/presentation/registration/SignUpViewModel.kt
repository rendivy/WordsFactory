package ru.yangel.auth_feature.presentation.registration

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yangel.auth_data.storage.repository.AuthRepository
import ru.yangel.auth_feature.presentation.login.state.LoginError
import ru.yangel.auth_feature.presentation.login.state.LoginState
import ru.yangel.auth_feature.presentation.registration.state.RegistrationError
import ru.yangel.auth_feature.presentation.registration.state.RegistrationState
import ru.yangel.auth_feature.presentation.registration.state.RegistrationUiState
import ru.yangel.core.customexception.AuthCollisionException
import ru.yangel.core.customexception.AuthException
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _registrationUiState = MutableStateFlow(RegistrationUiState())
    val registrationUiState = _registrationUiState.asStateFlow()

    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Initial)
    val registrationState = _registrationState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        when (exception) {
            is AuthCollisionException -> {
                _registrationState.value =
                    RegistrationState.Error(RegistrationError.EMAIL_ALREADY_BUSY)
            }

            else -> {
                _registrationState.value = RegistrationState.Error(RegistrationError.NETWORK_ERROR)
            }
        }
    }

    fun onNameChange(name: String) {
        _registrationUiState.value = _registrationUiState.value.copy(name = name)
    }

    fun onEmailChange(email: String) {
        _registrationUiState.value = _registrationUiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _registrationUiState.value = _registrationUiState.value.copy(password = password)
    }


    fun onSignUpClick() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _registrationState.value = RegistrationState.Loading
            authRepository.registerUser(
                email = _registrationUiState.value.email,
                password = _registrationUiState.value.password
            )
            _registrationState.value = RegistrationState.Success
        }
    }

}