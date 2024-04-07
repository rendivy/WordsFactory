package ru.yangel.splash_feature.presentation.state

sealed class SplashState {
    data object Initial : SplashState()
    data object NotLogin : SplashState()
    data object OnboardingNotPassed : SplashState()
    data object AlreadyLogin : SplashState()
}