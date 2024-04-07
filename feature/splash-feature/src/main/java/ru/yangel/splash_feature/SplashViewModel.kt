package ru.yangel.splash_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.yangel.auth_data.storage.repository.AuthRepository
import ru.yangel.splash_feature.presentation.state.SplashState
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _splashState = MutableStateFlow<SplashState>(SplashState.Initial)
    val splashState = _splashState.asStateFlow()


    fun isOnboardingPassed() {
        authRepository.isOnboardingPassed().onEach { isOnboardingPassed ->
            if (isOnboardingPassed) {
                checkUserLogin()
            } else {
                _splashState.value = SplashState.OnboardingNotPassed
            }
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    fun checkUserLogin() {
        _splashState.value = if (authRepository.isUserLogin()) {
            SplashState.AlreadyLogin
        } else {
            SplashState.NotLogin
        }
    }
}