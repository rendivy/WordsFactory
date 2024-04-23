package com.yuriyyangel.wordsfactory.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.yangel.core.usecase.ValidateEmailUseCase
import ru.yangel.core.usecase.ValidateNameUseCase
import ru.yangel.core.usecase.ValidatePasswordUseCase
import ru.yangel.core.validation.ValidateEmailUseCaseImpl
import ru.yangel.core.validation.ValidateNameUseCaseImpl
import ru.yangel.core.validation.ValidatePasswordUseCaseImpl


@InstallIn(ViewModelComponent::class)
@Module
interface AuthModuleDependencies {

    @Binds
    fun validateEmailUseCase(validateEmailUseCaseImpl: ValidateEmailUseCaseImpl): ValidateEmailUseCase

    @Binds
    fun validatePasswordUseCase(validatePasswordUseCaseImpl: ValidatePasswordUseCaseImpl): ValidatePasswordUseCase

    @Binds
    fun validateNameUseCase(validateNameUseCaseImpl: ValidateNameUseCaseImpl): ValidateNameUseCase

}