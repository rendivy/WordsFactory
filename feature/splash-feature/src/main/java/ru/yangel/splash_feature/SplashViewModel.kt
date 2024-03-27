package ru.yangel.splash_feature

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.yangel.auth_data.storage.repository.AuthRepository
import ru.yangel.splash_feature.presentation.state.SplashState
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    private val _splashState = MutableStateFlow<SplashState>(SplashState.Initial)
    val splashState = _splashState.asStateFlow()

    fun checkUserLogin() {
        _splashState.value = if (authRepository.isUserLogin()) {
            SplashState.AlreadyLogin
        } else {
            SplashState.NotLogin
        }
    }
}