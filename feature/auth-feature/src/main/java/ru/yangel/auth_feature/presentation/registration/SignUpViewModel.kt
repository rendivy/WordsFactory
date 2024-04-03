package ru.yangel.auth_feature.presentation.registration

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
import ru.yangel.auth_feature.presentation.registration.state.RegistrationError
import ru.yangel.auth_feature.presentation.registration.state.RegistrationState
import ru.yangel.auth_feature.presentation.registration.state.RegistrationUiState
import ru.yangel.core.customexception.AuthCollisionException
import ru.yangel.core.usecase.ValidateEmailUseCase
import ru.yangel.core.usecase.ValidateNameUseCase
import ru.yangel.core.usecase.ValidatePasswordUseCase
import ru.yangel.core.validation.ValidateEmailUseCase
import ru.yangel.core.validation.ValidateNameUseCase
import ru.yangel.core.validation.ValidatePasswordUseCase
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

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

    private fun registerUser() {
        _registrationState.value = RegistrationState.Loading
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            authRepository.registerUser(
                email = _registrationUiState.value.email,
                password = _registrationUiState.value.password
            )
            _registrationState.value = RegistrationState.Success
        }
    }


    private fun isInputValid(): Boolean {
        val isNameValid = validateNameUseCase.execute(_registrationUiState.value.name).isSuccessful
        val isEmailValid =
            validateEmailUseCase.execute(_registrationUiState.value.email).isSuccessful
        val isPasswordValid =
            validatePasswordUseCase.execute(_registrationUiState.value.password).isSuccessful

        return isNameValid && isEmailValid && isPasswordValid
    }


    fun onSignUpClick() {
        _registrationState.value = RegistrationState.Loading
        when (isInputValid()) {
            true -> registerUser()
            false -> _registrationState.value =
                RegistrationState.Error(RegistrationError.VALIDATION_ERROR)
        }
    }
}