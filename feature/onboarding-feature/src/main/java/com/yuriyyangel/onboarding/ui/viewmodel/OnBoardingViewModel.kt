package com.yuriyyangel.onboarding.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yangel.auth_data.storage.repository.AuthRepository
import javax.inject.Inject


@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {

    fun passOnBoarding() {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.loginUser()
        }
    }

}