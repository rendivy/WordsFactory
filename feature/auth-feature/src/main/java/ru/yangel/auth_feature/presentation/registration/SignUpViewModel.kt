package ru.yangel.auth_feature.presentation.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yangel.auth_data.storage.repository.AuthRepository
import ru.yangel.auth_feature.presentation.registration.state.RegistrationState
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _signUpState = MutableStateFlow(RegistrationState())
    val signUpState = _signUpState.asStateFlow()

    fun onNameChange(name: String) {
        _signUpState.value = _signUpState.value.copy(name = name)
    }

    fun onEmailChange(email: String) {
        _signUpState.value = _signUpState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _signUpState.value = _signUpState.value.copy(password = password)
    }

    fun onSignUpClick() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.registerUser(
                email = _signUpState.value.email,
                password = _signUpState.value.password
            )
        }
    }

}